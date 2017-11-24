Here you can find the ESP download tool and the AT firmare to be flashed to ESP module.


Advantages of this Tutorial: 
1. You can Flash ESP8266 AT firmware using Arduino only.
2. No need of USB to TTL converter.
3. Bricked ESP modules can be repaired using this tutorial.
4. Baudrate can defined as "9600" same as arduino.

The firmware here is "esp_iot_sdk_v1.5.0" and download Tool is "FLASH_DOWNLOAD_TOOLS_v2.4_150924".
Download and Extract the ESP8266 folder.

Warning: The ESP8266 tx and rx pins are rated as 3.3v and arduino uses 5v. Arduino may burn the Wifi Module but it worked for me and many users. So keep in mind I am not responsible for any burnouts, you are always adviced to use voltage divider circuit to divide 5v to 3.3v. And also Arduino doesn't provide steady regulated 3.3v for power supply but still it can be used for powering ESP8266.

Now, for the Connection Part (ESP8266-01 Pin diagram is also attached):

1. Arduino_RESET <------> Arduino_GND        (To use arduino as a USB to Serial).
2. ESP_GPIO_0   <-------> Arduino_GND        (To use ESP in flash mode).
3. ESP_TXd      <-------> Arduino_TX         (Yes, it sounds crazy. tx should be connected to rx but trust me).
4. ESP_RXd      <-------> Arduino_RX
5. ESP_CH_PD    <-------> Arduino_3.3v       (To enable Esp chip).
6. ESP_Vcc      <-------> Arduino_3.3v       (Esp power Supply).
7. ESP_GND      <-------> Arduino_GND        (Isn't it obvious).

Connect the Arduino to Computer.
Now lets flash the ESP AT firmware:
1. Open "ESP_DOWNLOAD_TOOL_V2.4.exe".
2. Choose the firmware path and address as: 
   1. path-> "\esp_iot_sdk_v1.5.0\bin\blank.bin" ADDR-> "0x7e000".
   2. path-> "\esp_iot_sdk_v1.5.0\bin\at\noboot\eagle.flash.bin" ADDR-> "0x00000".
   3. path-> "\esp_iot_sdk_v1.5.0\bin\blank.bin" ADDR-> "0xfe000".
   4. path-> "\esp_iot_sdk_v1.5.0\bin\at\noboot\eagle.irom0text.bin" ADDR-> "0x40000".
3. CrystalFreq: 26M
4. Flash Size: 8Mbit
5. SPI Speed: 40MHz
6. BAUDRATE: 345600
7. Now Click START. If Everything goes fine you will see the MAC Address.
8. Now close the flash download tool and open Arduino ide.
9. Disconnect the Arduino. 
10. Now remove ESP_GPIO_0 pin from ground to operate as operation mode and disconnect the Arduino_RESET from GND.
11. Upload Blank Sketch to Arduino or "bare minimum" Sketch from Examples.
12. Open Serial Monitor.
13. Set Baud Rate to "115600" and Send "AT" if you receive "OK" then move on or try different BaudRates.
14. Send Following Command: AT+UART_DEF=9600,8,1,0,0
15. You will receive "OK".
16. Now Change Serial Monitor Baud rate to "9600" and Send "AT"
17. If you receive "OK", congratulation the firmware is flashed successfully now you can use different AT commands from the AT command Set attached on this folder.
