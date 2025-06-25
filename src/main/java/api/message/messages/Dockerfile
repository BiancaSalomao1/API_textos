# Estágio de build: imagem já com Maven + JDK 21
FROM maven:3.9.6-eclipse-temurin-21 AS build-stage

WORKDIR /app

# Copia o POM e as fontes
COPY pom.xml .
COPY src ./src

# Compila o projeto sem rodar testes
RUN mvn clean install -Dmaven.test.skip=true

# Estágio de produção: imagem mais leve só com JDK
FROM eclipse-temurin:21-jdk-alpine AS production-stage

WORKDIR /app

# Copia o JAR gerado no build
COPY --from=build-stage /app/target/*.jar app.jar

EXPOSE 8080

# Executa o JAR
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
