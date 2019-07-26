#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>
#include <signal.h>
#include "builtins.h"
#define BUFFER_SIZE 1024
#define ARG_SIZE 64
/*
char *read_line(){
    char *line = NULL;
    ssize_t bufsize = 0; // have getline allocate a buffer for us
    getline(&line, &bufsize, stdin);
    return line;
}
*/
char *read_line(){
    int c, position=0;
    int buffer_size = BUFFER_SIZE;
    char *buffer = malloc(sizeof(char)*buffer_size);
    if(!buffer){
        fprintf(stderr, "MyShell: Buffer allocation error!\n");
        exit(EXIT_FAILURE);
    }
    while(1){
        c = getchar();
        if(c==EOF || c=='\n'){
            buffer[position] = '\0';
            return buffer;
        }
        buffer[position++] = c;
        if(position >= buffer_size){
            buffer_size += BUFFER_SIZE;
            buffer = realloc(buffer, sizeof(char)*buffer_size);
            if(!buffer){
                fprintf(stderr, "MyShell: Buffer allocation error!\n");
            }
        }
    }
    return buffer;
}

char **get_args(char *line){
    int i=0, count=0, spaced_arg=0, index=0,j=0;
    int arg_size = ARG_SIZE;
    char **args = (char **)malloc(arg_size*sizeof(char *));

    char *arg = (char *)malloc(arg_size*sizeof(char));
    if(!args || !arg){
        fprintf(stderr, "MyShell: Buffer allocation failure!\n");
        exit(EXIT_FAILURE);
    }
    //trimming white spaces from beginning of line
    while(line[i]==' ')
        i++;
    while(line[i] != '\0'){
        if(line[i]==' '){
            arg[j] = '\0';
            args[count] = arg;
            arg = (char *)malloc(arg_size*sizeof(char));
            count++;
            j=0;
            while(line[i]==' ' && line[i]!='\0')
                i++;
        }
        else{
            arg[j++] = line[i++];
        }
    }
    //getting last argument
    if(strlen(arg)==0){
        args[count] = NULL;
    }
    else{
        arg[j]='\0';
        args[count++]=arg;
        args[count]=NULL;
    }
    return args;
}
int execute_command(char **args){
    int status;
    pid_t pid, wpid;
    pid = fork();
    if(pid == 0){
	execvp(args[0], args);
	//After execvp return of unsuccessful execution.
	perror("MyShell");
	exit(EXIT_FAILURE);
    }
    else if(pid < 0){
	perror("MyShell");
    }
    else{
	do{
	    wpid = waitpid(pid, &status, WUNTRACED);
	}while(!WIFEXITED(status) && !WIFSIGNALED(status));
    }
    return 1;
}
int execute(char **args){
    int i;
    if(args[0] == NULL)
        return 1;
    for(i=0;i<num_builtins();i++){
        if(strcmp(args[0], str_builtins[i]) == 0){
            return (*fun_builtins[i])(args);
        }
    }
    return execute_command(args);
}
void loop(){
    char *line;
    char **args;
    int status=1;
    char cwd[100];
    do{
	    printf("[%s]:>>>", getcwd(cwd, 100));
        line = read_line();
        args = get_args(line);
        status = execute(args);
        
        free(line);
        free(args);
    }while(status);

}
int main(){
    printf("###########################################################\n");
    printf("#                       ---MY SHELL---                    #\n");
    printf("#                    Use at your own risk!                #\n");
    printf("###########################################################\n");
    loop();
    return 0;
}
