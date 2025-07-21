# Этап сборки
FROM maven:3.9.5-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn -f pom.xml clean package -DskipTests

# Финальный образ
FROM openjdk:17-alpine
LABEL authors="Ruslan Radevich"
WORKDIR /app
EXPOSE 8099

# Копируем финальный JAR из нужного модуля
COPY --from=build /app/carsharing-impl/target/*.jar carsharing-impl.jar

# Запуск приложения
ENTRYPOINT ["java", "-jar", "carsharing-impl.jar"]