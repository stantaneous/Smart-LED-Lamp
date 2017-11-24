This is the arduino code to be uploaded to the Arduino Uno.

Make Sure to have AT Firmware to ESP8266 and change the BaudRate to "9600"

Connections:

Arduino_GND ------- ESP_GND
Arduino_2   ------- ESP_TXD
Arduino_3   ------- ESP_RXD
Arduino_3.3v------- ESP_CH_PD
Arduino_3.3v------- ESP_Vcc

Arduino_7------LED_RED
Arduino_6------LED_GREEN
Arduino_5------LED_BLUE

Now define your home network ssid and password in the "Smartled.ino" code and upload the code to the Arduino.

After Uploading Code open Serial Monitor and wait for the ip address to be shown in the Serail Monitor.
Note down the ip address, you will be needing this in Android programming.

If something goes wrong just open Arduino code and go to setup() function and in the sendcommand callings change the last parameter to "true".
Now you can see whats the problem using the serial monitor.
