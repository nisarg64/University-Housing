## Project : University Student Housing
---
#####Software : `Maven 3.2.5, Java-7, Oracle 10g, Apache-tomcat7`
#####OS : `Ubuntu 12.04 (Linux 3.2.0-58-generic), Windows 7`
##### Team : `Anand Bora(abora), Abhishek Agrawal(akagrawa), Nikhil Dalmia(nkdalmia), Nisarg Gandhi(ndgandh2)`
 ---
### Installation Guide 
Install software packages:  
(a) Download [**Apache Tomcat**](https://tomcat.apache.org/download-70.cgi) from the Apache tomcat web-site.   
(b) Download and install [**Java 7**](http://java.com/en/download/  ).  
(c) Download and install [**Maven**](http://maven.apache.org/download.cgi).  
(d) For Oracle refer classroom installation guide
**Note:** Please skip the above installations, if already installed.  

## Environment Variables

$TOMCAT = /YOUR TOMCAT PATH/
$JAVA_HOME = /YOUR JAVA HOME PATH/

## Execution Commands
[1] ** For Seed Data Entry in DB ** : Run below command
```
    java -cp uhousing.jar db.BootStrap &
```
[2] ** For User Interface ** : Place the uhousing.war file in tomcat/webapps folder and start the tomcat server.

```
    cp uhousing.war $TOMCAT/webapps/
    cd $TOMCAT/bin
    ./startup.sh
```
Now open the web browser and hit the url below:

```
    http://localhost:8080/uhousing
```
Note : Make sure your 8080 port is available. Or else change the server.xml in tomcat conf folder for any other available port.



