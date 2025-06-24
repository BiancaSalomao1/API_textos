# Etapa de build
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copia todo o código fonte
COPY . .

# Faz o build do projeto e ignora os testes
RUN mvn clean install -DskipTests

# Etapa de execução
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copia o JAR gerado do build anterior
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta
EXPOSE 8080

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]
