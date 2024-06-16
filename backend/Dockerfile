FROM openjdk:17-jdk-alpine

EXPOSE 8080

COPY . .

RUN ./mvnw package

CMD ["java","-jar","./target/backend-0.0.1-SNAPSHOT.jar"]