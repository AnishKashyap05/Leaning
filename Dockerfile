# Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file into the container
COPY target/myspring-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port (replace with your application port if different)
EXPOSE 8090

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
