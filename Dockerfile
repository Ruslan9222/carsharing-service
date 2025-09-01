FROM maven:3.9.5-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn -f pom.xml clean package -DskipTests

FROM openjdk:17-alpine
LABEL authors="Ruslan Radevich"
WORKDIR /app
EXPOSE 8099

COPY --from=build /app/carsharing-impl/target/*.jar carsharing-impl.jar

ENTRYPOINT ["java", "-jar", "carsharing-impl.jar"]