# Etapa 1: build com Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

COPY . /app

RUN mvn clean package -DskipTests

# Etapa 2: runtime
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
