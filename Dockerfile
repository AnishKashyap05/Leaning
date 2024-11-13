# Use a Maven base image to build the app
FROM maven:3.8.4-openjdk-17-slim AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and the source code to the container
COPY pom.xml .
COPY src ./src

# Build the Spring Boot application
RUN mvn clean package -DskipTests

# Use OpenJDK 17 image to run the app
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/myspring-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port (replace with your application port if different)
EXPOSE 8090

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
