# Etapa de build (opcional, caso queira construir no container)
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copia o projeto para o container
COPY . .

# Compila o projeto e gera o JAR
RUN mvn clean package install -DskipTests

# Etapa final: imagem leve para rodar a aplicação
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copia o JAR gerado na etapa de build
COPY --from=build /app/target/*.jar app.jar

# Define o comando de execução
ENTRYPOINT ["java", "-jar", "app.jar"]
