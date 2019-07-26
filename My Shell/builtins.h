#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int fun_cd(char **);
int fun_help(char **);
int fun_exit(char **);

int (*fun_builtins[]) (char **) = {
  &fun_cd,
  &fun_help,
  &fun_exit
};

char * str_builtins[] = {
    "cd",
    "help",
    "exit"
};

int num_builtins(){
    return sizeof(str_builtins)/sizeof(char *);
}

int fun_cd(char **args)
{
  if (args[1] == NULL) {
    fprintf(stderr, "MyShell: expected argument to \"cd\"\n");
  }
  else {
    if (chdir(args[1]) != 0) {
      perror("MyShell");
    }
  }
  return 1;
}

int fun_help(char **args)
{
  int i;
  printf("Type program name and arguments, and hit enter.\n");
  printf("The following are built in functions:\n");

  for (i = 0; i < num_builtins(); i++) {
    printf("  %s\n", str_builtins[i]);
  }

  printf("Use the man command for information on other programs.\n");
  return 1;
}

int fun_exit(char **args)
{
  return 0;
}
