FROM openjdk:17-jdk-slim

WORKDIR /tmp

COPY target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
