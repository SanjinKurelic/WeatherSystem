# WeatherSystem

Weather system is a set of applications which allows user to list, create, update or remove weather station and also allows user to fetch and log current weather report from the weather station. Whole system is consisted from three main parts:

- Client application for managing weather stations
- Server which handles all logic regarding weather stations, and also it's reading weather reports and send them to MQ server
- Enterprise service bus which glue all systems to work together

An enterprise service bus (ESB) implements a communication system between mutually interacting software applications in a service-oriented architecture (SOA). It represents a software architecture for distributed computing<sup>[1]</sup>.

## Getting started

### Prerequisites

For running the project you need to have the following items:

- ActiveMQ running on default port (8161)
- installed Mule ESB software
- Java/JDK 17

### Running

#### Active MQ

Start ActiveMQ with default settings (port = 8161). Spring Boot server (when started) will automatically send MQ messages, as shown on image below.

![](https://github.com/SanjinKurelic/WeatherSystem/blob/master/images/mqWeatherReport.png)

No extra settings are needed, MQ queue will be automatically crated.

#### Spring boot backend

Open `WeatherSystemServer` directory and run following command to start the server:

```
./mvnw spring-boot:run
```

Java 17 is required for sucessfully running above command. Maven wrapper which is included in project will automatically fetch all needed dependencies ans it'll start server at port 8085. By importing `WeatherSystem.postman_collection.json` file to Postman, server API and status could be easily tested. All CRUD responses from server will be in HAL JSON format.

#### Mule ESB

Open Mule Anypoint Studio and import project from `WeatherSystemESB` directory. ESB is main glue for connecting all systems to work together. Flows are separated to three different logical units described in following paragraphs.

##### Station API flow

Flow which define connection Client <-> Backend. All requests from client go through ESB and are redirected to server. Server will response in HAL JSON which will then be transformed to basic JSON for easily integration. All requests are logged by Logger flow.

![](https://github.com/SanjinKurelic/WeatherSystem/blob/master/images/muleStationAPI.png)

##### MQ flow

MQ flow consist of listener for new MQ messages, transformer which transform message to more readable format and call to Logger flow which will log output of MQ message.

![](https://github.com/SanjinKurelic/WeatherSystem/blob/master/images/muleMq.png)

##### Logger flow

Flow responsible for logging the actions. All actions are saved in `log.txt` file.

![](https://github.com/SanjinKurelic/WeatherSystem/blob/master/images/muleAPILogger.png)

#### JavaFX client

Client application is responsable for getting, adding, updating and removing weather stations. Fronted is written using Java FX library. 
Open `WeatherSystemClient` directory and run following command to start the application:

```
mvn clean javafx:run
```

If above command does not work, application could be started using Java IDE (ex. Intellij IDEA). There are some known issues when running without IDE.

![](https://github.com/SanjinKurelic/WeatherSystem/blob/master/images/clientApp.png)

### Business process diagrams

Image below show one bussines process for using this system. Diagrams are created and executed in jBPM.

![](https://github.com/SanjinKurelic/WeatherSystem/blob/master/images/jbmpDiagram.png)

jBPM (Java Business Process Model) is an open-source workflow engine written in Java that can execute business processes described in BPMN 2.0.

![](https://github.com/SanjinKurelic/WeatherSystem/blob/master/images/jbpmProject.png)

## Dependencies

Several technologies were used while developing this system:

- ActiveMQ
- MuleESB
- Spring Boot
- JavaFx
- Spring Data
- Repository REST
- JPA
- JMS
- Scheduler
- RestTemplate
- H2
- Lombok

## References

<sup>[1]</sup> https://en.wikipedia.org/wiki/Case-based_reasoning

## Licence

See the LICENSE file. For every question write to kurelic@sanjin.eu
