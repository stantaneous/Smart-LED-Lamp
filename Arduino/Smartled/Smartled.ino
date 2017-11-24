#include<SoftwareSerial.h>
#define RED_PIN 7
#define GREEN_PIN 6
#define BLUE_PIN 5
#define Check_Led LED_BUILTIN
String ssid = "Wlan";
String password = "password";
 
SoftwareSerial esp8266(2,3);    //2=>tx 3=>rx

String sendCommand(String command, const int timeout, boolean debug)
{
    String response = ""; 
    esp8266.print(command); 
    long int time = millis();
    while( (time+timeout) > millis())
    {
      while(esp8266.available())
      {
         
        char c = esp8266.read();
        response+=c;
      }
    }
    if(debug)
    {
      Serial.println(response);
    }
    return response;
}

void setup() {
  pinMode(RED_PIN, OUTPUT);
  pinMode(GREEN_PIN, OUTPUT);
  pinMode(BLUE_PIN, OUTPUT);
  pinMode(Check_Led, OUTPUT);
  Serial.begin(9600);
  esp8266.begin(9600);
  String wifi = "AT+CWJAP="+ssid+","+password+"\r\n";
  sendCommand("AT+RST\r\n",600,false); // reset module
  sendCommand("AT+CWMODE=1\r\n",400,false); // configure as access point
  sendCommand(wifi,2000,false); // join ssid = wlan & pass = password
  delay(3000);
  sendCommand("AT+CIPMUX=1\r\n",1000,false); // configure for multiple connections
  sendCommand("AT+CIPSERVER=1,80\r\n",1000,false); // turn on server on port 80
  Serial.println("Server Ready");
  sendCommand("AT+CIFSR\r\n",1000,true);
  digitalWrite(Check_Led, HIGH);   
  delay(1000);                     
  digitalWrite(Check_Led, LOW);
}

void loop() {
  if(esp8266.available()) // check if the esp is sending a message 
  {
    if(esp8266.find("+IPD,")) // find if client connected
    {
     delay(100);
     //find Color                         
     esp8266.find("Color="); 
     String color = "";
     //read the numbers of color 
     int c = (esp8266.read());
     while(c!=';'){
      color += (char)c;
      c = (esp8266.read());
     }
     // write the color to led
     setColor(color.toInt());
     // make close command
     String closeCommand = "AT+CIPCLOSE=0\r\n"; 
     sendCommand(closeCommand,400,false); // close connection
    }
  }
}
void setColor(long int color)
{
  int red = (color >> 16) & 0xff;
  int green = (color >> 8) & 0xff;
  int blue = color & 0xff;
  Serial.print("Red: ");
  Serial.println(red);
  Serial.print("Green: ");
  Serial.println(green);
  Serial.print("Blue: ");
  Serial.println(blue);
  analogWrite(RED_PIN, red);
  analogWrite(GREEN_PIN, green);
  analogWrite(BLUE_PIN, blue);
}

