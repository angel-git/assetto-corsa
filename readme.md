# Assetto Corsa telemetry viewer

Spring boot application that connects to Assetto corsa and shows some stats.
You can access to it with your phone/tablet in you are in the same network.

## requirements
- java jdk 1.8
- maven

## run
in the root of the project:

- compile the code with: ``mvn clean install``
- execute code with: ``java -jar assetto-api/assetto-api-ui/target/assetto-api-ui-**version**.jar``

## connect
go to ``http://localhost:8080``

## Improvements
use websocket instead of polling?

