FROM maven:3.9.4-eclipse-temurin-21 AS build

COPY . /app
WORKDIR /app

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

COPY src/main/resources/static /app/src/main/resources/static

COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
