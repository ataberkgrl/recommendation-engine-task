FROM adoptopenjdk/openjdk11:latest
COPY target/recommendation-api-0.0.1-SNAPSHOT.jar recommendation-api-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "recommendation-api-0.0.1-SNAPSHOT.jar"]