# Estágio de Build
# Usar uma imagem base com Temurin JDK 21 e Maven 3.9 (ou a versão mais recente
estável)
FROM eclipse-temurin:21-jdk-alpine AS build-stage
# Definir o diretório de trabalho dentro do contêiner
WORKDIR /app
# Copiar o arquivo pom.xml para que as dependências sejam baixadas primeiro
(otimização de cache)
COPY pom.xml .
# Baixar as dependências do Maven. O 'go-offline' ajuda a garantir que todas as
dependências estejam disponíveis.
RUN mvn dependency:go-offline
# Copiar o código fonte da aplicação
COPY src ./src
# Compilar a aplicação, pulando os testes para agilizar o build
RUN mvn clean install -Dmaven.test.skip=true
# Estágio de Produção
# Usar uma imagem base leve com apenas o JDK 21 para a execução da aplicação
FROM eclipse-temurin:21-jdk-alpine AS production-stage
# Definir o diretório de trabalho
WORKDIR /app
# Copiar o JAR compilado do estágio de build para o estágio de produção
# O nome do JAR pode variar, então usamos um wildcard
COPY --from=build-stage /app/target/*.jar app.jar
# Expor a porta que a aplicação Spring Boot usa (padrão: 8080)
EXPOSE 8080
# Comando para executar a aplicação Spring Boot
ENTRYPOINT ["java", "-jar", "/app/app.jar"]