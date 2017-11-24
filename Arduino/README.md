This is the arduino code to be uploaded to the Arduino Uno.

Make Sure to have AT Firmware to ESP8266 and change the BaudRate to "9600"

Connections:

1. Arduino_GND ------- ESP_GND
2. Arduino_2   ------- ESP_TXD
3. Arduino_3   ------- ESP_RXD
4. Arduino_3.3v------- ESP_CH_PD
5. Arduino_3.3v------- ESP_Vcc
6. Arduino_7------LED_RED
7. Arduino_6------LED_GREEN
8. Arduino_5------LED_BLUE

Now define your home network ssid and password in the "Smartled.ino" code and upload the code to the Arduino.

After Uploading Code open Serial Monitor and wait for the ip address to be shown in the Serail Monitor.
Note down the ip address, you will be needing this in Android programming.

If something goes wrong just open Arduino code and go to setup() function and in the sendcommand callings change the last parameter to "true".
Now you can see whats the problem using the serial monitor.
