
FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/coindesk-0.0.1-SNAPSHOT.jar /app/coindesk-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/coindesk-0.0.1-SNAPSHOT.jar"]
