# Spring Boot with ActiveMQ 5
This is demo project demonstrating Spring Boot with ActiveMQ using Topics. 
The Sender is sending messages to a topic named __monkey-island-characters__
and a Receiver service is listening for those messages. 
Messages are send as strings and the message body (Character.java) is a name/surname from Monkey Island game (best game ever!). 
The Sender service is converting the Character object to string and publishes it to the topic. 

# Configuration
In application.properties file you can see how we set the properties for ActiveMQ. 
We are using the default username and password (admin/admin).
In order to use Topics (broadcast) and not Queues (serial) we have to set to TRUE the following property:
```
spring.jms.pub-sub-domain
```
We also set the name of the topic here, using a custom property name (destination.name).

# Sending messages to topic
In order to send a message to the topic, we first have to inject the JmsTemplate. 
We inject it in our service class (CharacterSrv.java) and then we send the messages using the following code:
```
jmsTemplate.convertAndSend(destinationName, message);
```
Here, __destinationName__ is the name of the topic and __message__ is pretty obvious.

# Run the application
Open a console window and run the following commands while inside the project folder:
```
mvn clean package
java -jar target/amqSender-0.0.1-SNAPSHOT.jar
```

**Keep in mind that you have to start the ActiveMQ first:**
For Windows users:
- Download it and extract it
- Go to <downloaded_folder>/bin/<win_version>
- Run activeqm.bat 

For Linux and Mac:
Follow the manual :)

Then make POST requests to send messages. 

[EXAMPLE] - Text
For simple string messages use this endpoint:
```
http://localhost:8000/api/message/GuybrushThreepwood
``` 

[EXAMPLE] - Character object as text
For object as string messages use this endpoint:
```
http://localhost:8000/api/message/
``` 
and the body of the request could be this for example:
```
{"name":"Guybrush", "surname":"Treepwood"}
```