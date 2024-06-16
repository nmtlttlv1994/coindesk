FROM openjdk:17-jdk-slim
ADD target/*.jar app.jar

ENV ENV_DATASOURCE_USERNAME=sa
ENV ENV_DATASOURCE_PASSWORD=123456

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
