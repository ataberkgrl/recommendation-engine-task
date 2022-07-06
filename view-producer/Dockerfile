FROM adoptopenjdk/openjdk11:latest
COPY target/viewproducer-0.0.1-SNAPSHOT.jar viewproducer-0.0.1-SNAPSHOT.jar
COPY product-views.json product-views.json
ENTRYPOINT ["java", "-jar", "viewproducer-0.0.1-SNAPSHOT.jar"]