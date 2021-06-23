# SmsGateway
Andriod app for SMS Gateway

This simple app will act as Sms Gateway, install on Android device/phone and access this app url over wifi which send sms to receipent.
This poroject use nanohttpd for incoming http request.

App Icon

![Alt text](AppIcon.png)

At the time of installation unsafe warning will appear as this app is for demo and using selfsigned cert. 
Download the SmsGateway.apk file and install,post installation, goto Settings --> Apps --> TroySms --> Permissions --> SMS permission (allow).

![alt text] (TroySms-Server.jpg)

Run the application, it will display server listen address and port of wifi connections.
Open a browser on any laptop/machine connected to same wifi netowrk and hit the url http://IP:port/?cell_number=9923xxxxx&message=Hello1

![alt test] ()





