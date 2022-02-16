# Weather System

Weather system is a set of services which allows a user to manage weather stations and weather reports. User can  list, create, update or remove weather stations using desktop application. She or he can also check log files to read current weather report on the weather station. Fetching weather report is done through scheduler service developed on backend server. Integration and logging is done thought MQ server and enterprise service bus. An enterprise service bus (ESB) implements a communication system between mutually interacting software applications in a service-oriented architecture (SOA). It represents a software architecture for distributed computing<sup>[1]</sup>. Whole system is consisted from three main parts:

- Client application for managing weather stations
- Server which handles all logic regarding weather stations, and also it's reading weather reports and send them to MQ server
- Enterprise service bus which glue all systems to work together

## Getting started

To be able to run the project successfully, several technologies and programs are required.

### Prerequisites

For running the project, you need to have the following items:

- ActiveMQ
- Mule ESB Anypoint Studio
- Java/JDK 17

Maven is included as Maven wrapper inside `WeatherSystemServer` directory.

### Running

This project require that every component of a project is running.

#### Active MQ

ActiveMQ is used as a connection through which the backend server sends weather report to ESB. This is usually the case with older IOT systems, which can only communicate through a messaging system. ESB listens to every new weather report message and store them to the log files.

Start ActiveMQ with default settings (port = 8161). Spring Boot server (when started) will automatically send MQ messages, as shown on image below.

<p align="center"><img src="https://github.com/SanjinKurelic/WeatherSystem/blob/main/images/mqWeatherReport.png"/></p>

No extra settings are required - MQ queue will be automatically crated.

#### Spring boot backend

Open `WeatherSystemServer` directory and run the following command to start the server:

```
./mvnw spring-boot:run
```

Note, Java 17 is required for successfully running the above command. Maven wrapper which is included in the project will automatically fetch all required dependencies, and it'll start the server at port 8085. 

Server will expose CRUD REST API for managing weather stations. Those operations and statuses could be easily tested with Postman by importing `WeatherSystem.postman_collection.json` file as Postman collection. All CRUD operations from the server will be in HAL JSON format.

The server will also generate weather reports using scheduler every 20 seconds, and send them to MQ using JMS.

#### Mule ESB

Open Mule Anypoint Studio and import project from `WeatherSystemESB` directory. ESB is the main glue for connecting all systems to work together. Flows are separated to three different semantic units described in following paragraphs.

##### Station API flow

Flow which define connection between client application and backend service. All requests from the client go through ESB and are redirected to the server. The server will respond in HAL JSON, which will then be transformed to basic JSON (in ESB) for easily integration. All requests are logged by Logger flow. In flow there is also "choice" component which implements routing operations.

<p align="center"><img src="https://github.com/SanjinKurelic/WeatherSystem/blob/main/images/muleStationAPI.png"/></p>

##### MQ flow

MQ flow consist of listener for the new MQ messages, transformer which transform message to more readable format and call to Logger flow which will log output of MQ message.

<p align="center"><img src="https://github.com/SanjinKurelic/WeatherSystem/blob/main/images/muleMq.png"/></p>

##### Logger flow

Flow responsible for logging the actions. All actions are saved in `log.txt` file.

<p align="center"><img src="https://github.com/SanjinKurelic/WeatherSystem/blob/main/images/muleAPILogger.png"/></p>

#### JavaFX client

Client application is responsible for managing weather stations. User can list, add, edit or remove weather station. Fronted is written using Java FX library.

Open `WeatherSystemClient` directory and run the following command to start the application:

```
mvn clean javafx:run
```

If the above command does not work, the application could be started using Java IDE (ex. IntelliJ IDEA). There are some known issues when running the application without IDE.

<p align="center"><img src="https://github.com/SanjinKurelic/WeatherSystem/blob/main/images/clientApp.png"/></p>

## Business process diagrams

Image below show one of business process for using this system. Diagrams are created and executed in jBPM. 

<p align="center"><img src="https://github.com/SanjinKurelic/WeatherSystem/blob/main/images/jbmpDiagram.png"/></p>

jBPM (Java Business Process Model) is an open-source workflow engine written in Java that can execute business processes described in BPMN 2.0.

<p align="center"><img src="https://github.com/SanjinKurelic/WeatherSystem/blob/main/images/jbpmProject.png"/></p>

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
