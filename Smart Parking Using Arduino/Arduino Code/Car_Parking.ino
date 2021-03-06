#include <Ethernet.h>
#include <SPI.h>
#include <Servo.h>
Servo servo;

int available1;
int available2;
int available3;
int available4;
int available5;
//distance for teh ultrasonic sensor
long distance1;
long distance2;
long distance3;
long distance4;
long distance5;
//duration taken for the sensor to transmit ultrasonic waves
long duration1;
long duration2;
long duration3;
long duration4;
long duration5;
//opening the servo for the cars to enter
long servoduration;
long servodistance;
//the output pins for the sensor
#define trig1  26
#define trig2  28
#define trig3  30
#define trig4  32
#define trig5  36
//the input pins for the sensor
#define echo1  27
#define echo2  29
#define echo3  31
#define echo4  33
#define echo5  37
//the out and in pins for the sensor
#define servotrig 38
#define servoecho 39
//ethernet datapins
//char server[] = "192.168.137.177";
byte server[] = {192, 168, 137, 1};
EthernetClient client;
//default computer address
int led=5;
byte mac[] = {
  0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED
};
int response;
void setup() {
  Ethernet.begin(mac);
  Serial.begin (9600);
  delay(100);


  pinMode(trig1,OUTPUT);
  pinMode(trig2,OUTPUT);
  pinMode(trig3,OUTPUT);
  pinMode(trig4,OUTPUT);
  pinMode(trig5,OUTPUT);
  
  pinMode(echo1,INPUT);
  pinMode(echo2,INPUT);
  pinMode(echo3,INPUT);
  pinMode(echo4,INPUT);
  pinMode(echo5,INPUT);
  
  pinMode(servotrig,OUTPUT);
  pinMode(servoecho,INPUT);
 servo.attach(37);
}
void loop() {
    digitalWrite(trig1, LOW);  
  delayMicroseconds(2); 
  digitalWrite(trig1, HIGH);
  delayMicroseconds(10); 
  digitalWrite(trig1, LOW);
  duration1 = pulseIn(echo1, HIGH);
  distance1 = (duration1/2) / 29.1; 
  digitalWrite(trig2, LOW);  
  delayMicroseconds(2); 
  digitalWrite(trig2, HIGH);
  delayMicroseconds(10); 
  digitalWrite(trig2, LOW);
  duration2 = pulseIn(echo2, HIGH);
  distance2 = (duration2/2) / 29.1;
 digitalWrite(trig3, LOW);  
  delayMicroseconds(2); 
  digitalWrite(trig3, HIGH);
  delayMicroseconds(10); 
  digitalWrite(trig3, LOW);
  duration3 = pulseIn(echo3, HIGH);
  distance3 = (duration3/2) / 29.1;
  digitalWrite(trig4, LOW);  
  delayMicroseconds(2); 
  digitalWrite(trig4, HIGH);
  delayMicroseconds(10); 
  digitalWrite(trig4, LOW);
  duration4 = pulseIn(echo4, HIGH);
  distance4 = (duration4/2) / 29.1; 
  digitalWrite(trig5, LOW);  
  delayMicroseconds(2); 
  digitalWrite(trig5, HIGH);
  delayMicroseconds(10); 
  digitalWrite(trig5, LOW);
  duration5 = pulseIn(echo5, HIGH);
  distance5 = (duration5/2) / 29.1;
  Serial.print("Distance 1:");
  Serial.print(distance1);
  Serial.println("cm");
 Serial.print("Distance 2:");
  Serial.print(distance2);
  Serial.println("cm");
 Serial.print("Distance 3:");
  Serial.print(distance3);
  Serial.println("cm");
 Serial.print("Distance 4:");
  Serial.print(distance4);
  Serial.println("cm");
  Serial.print("Distance 5:");
  Serial.print(distance5);
  Serial.println("cm");
 delay(100);
//getting the actual value required to do the project successfully
 
  if(distance1<6)
  {
    available1=0;
  }
  else{
    available1 =1;
  } 
 if(distance2<6)
  {
    available2=0;
  }
  else{
    available2 =1;
  }
  if(distance3<6)
  {
    available3=0;
  }
  else{
    available3 =1;
  }
 if(distance4<6)
  {
    available4=0;
  }
  else{
    available4 =1;
  }
  
  if(distance5<6)
  {
    available5=0;
  }
  else{
    available5 =1;
  }
  
  digitalWrite(servotrig, LOW);  
  delayMicroseconds(2); 
  digitalWrite(servotrig, HIGH); 
  delayMicroseconds(10); 
  digitalWrite(servotrig, LOW);
  servoduration = pulseIn(servoecho, HIGH);
  
    if(servodistance<10)
  {
    servo.write(90);
  }
  else
  {
    servo.write(-90);
  }
  if (!client.connected()) 
    {
      Serial.print("Available1=");
      Serial.println(available1);
      Serial.print("Available2=");
      Serial.println(available2);
      Serial.print("Available3=");
      Serial.println(available3);
      Serial.print("Available4=");
      Serial.println(available4);
      Serial.print("Available5=");
      Serial.println(available5);
      client.stop();
     delay(100);    
      if (client.connect(server, 80)) 
      {
        Serial.println("connected");
        // Make a HTTP request:
        client.print("GET /carparking/UpdateAvailability.php?Position=");
        client.print("1");
        client.print("&&");
        client.print("Available=");
        client.print(available1);
        client.println(" HTTP/1.1");
        client.println("Host: 192.168.137.1");
        client.println("Connection: close");
        client.println();
        client.stop();
       } 
      else 
      {
        // if you didn't get a connection to the server:
        Serial.println("connection failed");
      }
      if (client.connect(server, 80)) 
      {
        Serial.println("connected");
        // Make a HTTP request:
        client.print("GET /carparking/UpdateAvailability.php?Position=");
        client.print("2");
        client.print("&&");
        client.print("Available=");
        client.print(available2);
        client.println(" HTTP/1.1");
        client.println("Host: 192.168.137.1");
        client.println("Connection: close");
        client.println();
        client.stop();
       } 
      else 
      {
        // if you didn't get a connection to the server:
        Serial.println("connection failed");
      }
    
     if (client.connect(server, 80)) 
      {
        Serial.println("connected");
        // Make a HTTP request:
        client.print("GET /carparking/UpdateAvailability.php?Position=");
        client.print("3");
        client.print("&&");
        client.print("Available=");
        client.print(available3);
        client.println(" HTTP/1.1");
        client.println("Host: 192.168.137.1");
        client.println("Connection: close");
        client.println();
        client.stop();
       } 
      else 
      {
        // if you didn't get a connection to the server:
        Serial.println("connection failed");
      }
    
    
     if (client.connect(server, 80)) 
      {
        Serial.println("connected");
        // Make a HTTP request:
        client.print("GET /carparking/UpdateAvailability.php?Position=");
        client.print("4");
        client.print("&&");
        client.print("Available=");
        client.print(available4);
        client.println(" HTTP/1.1");
        client.println("Host: 192.168.137.1");
        client.println("Connection: close");
        client.println();
        client.stop();
       } 
      else 
      {
        // if you didn't get a connection to the server:
        Serial.println("connection failed");
      }
     if (client.connect(server, 80)) 
      {
        Serial.println("connected");
        // Make a HTTP request:
        client.print("GET /carparking/UpdateAvailability.php?Position=");
        client.print("5");
        client.print("&&");
        client.print("Available=");
        client.print(available5);
        client.println(" HTTP/1.1");
        client.println("Host: 192.168.137.1");
        client.println("Connection: close");
        client.println();
        client.stop();
       } 
      else 
      {
        // if you didn't get a connection to the server:
        Serial.println("connection failed");
      }
    
    }
}


