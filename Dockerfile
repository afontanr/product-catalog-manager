# Use Maven with OpenJDK 17 as the base image for building the application
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project descriptor (pom.xml) to the container
COPY pom.xml .

# Download all required dependencies to speed up future builds
RUN mvn dependency:go-offline

# Copy the application source code into the container
COPY src ./src

# Build the application and create the JAR file, skipping tests for faster builds
RUN mvn clean package -DskipTests

# Use OpenJDK 17 as the runtime environment
FROM openjdk:17

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the build stage to the runtime stage
COPY --from=build /app/target/product-catalog-manager-0.0.1-SNAPSHOT.jar product-catalog-manager.jar

# Expose port 8080 to allow external access to the application
EXPOSE 8080

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "product-catalog-manager.jar"]
