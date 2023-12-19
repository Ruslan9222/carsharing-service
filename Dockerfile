#FROM maven:3.9.5-eclipse-temurin-17 AS build
#WORKDIR /app
#COPY pom.xml .
#COPY src ./src
#RUN mvn package

FROM openjdk:17-alpine
WORKDIR /app
COPY target/*.jar app.jar
#COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
