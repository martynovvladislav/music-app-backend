FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/search-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "app.jar"]
