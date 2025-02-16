# Etapa 1: Construcción de la aplicación
FROM maven:3.8.4-openjdk-17 AS build

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el código fuente y compilar el JAR
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen de ejecución
FROM openjdk:17

# Establecer el directorio de trabajo para la aplicación
WORKDIR /app

# Copiar el archivo JAR desde la etapa de construcción
COPY --from=build /app/target/product-catalog-manager-0.0.1-SNAPSHOT.jar product-catalog-manager.jar

# Exponer el puerto en el que la aplicación estará escuchando
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "product-catalog-manager.jar"]
