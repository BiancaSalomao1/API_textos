# Estágio de build com Maven e JDK 21
FROM maven:3.9.6-eclipse-temurin-21 AS build-stage

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean install -Dmaven.test.skip=true

# Estágio de produção com apenas JDK
FROM eclipse-temurin:21-jdk-alpine AS production-stage

WORKDIR /app

COPY --from=build-stage /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
