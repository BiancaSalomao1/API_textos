# Etapa 1 - Build com Maven atualizado + Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

# Etapa 2 - Runtime somente com Java (leve e otimizado)
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
