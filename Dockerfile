FROM openjdk:17-alpine

COPY target/*.jar neoris.jar

ENTRYPOINT [ "java", "-jar", "/neoris.jar" ]