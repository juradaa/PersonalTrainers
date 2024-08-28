FROM openjdk:17-jdk-alpine AS build

WORKDIR /usr/src/app

EXPOSE 8080

COPY . .

RUN ./mvnw package

FROM openjdk:17-jdk-alpine

WORKDIR /usr/src/app

COPY --from=build /usr/src/app/target/backend-0.0.1-SNAPSHOT.jar /usr/src/app/app.jar

RUN addgroup -S spring && adduser -S spring -G spring

USER spring:spring

CMD ["java","-jar","app.jar"]