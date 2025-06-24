FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copia apenas o pom.xml para resolver as dependências primeiro
COPY pom.xml .

# Baixa as dependências antes de copiar o resto do código
RUN mvn dependency:go-offline

# Agora copia todo o restante do projeto
COPY . .

# Faz o build da aplicação
RUN mvn clean package -DskipTests

# Imagem de runtime
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
