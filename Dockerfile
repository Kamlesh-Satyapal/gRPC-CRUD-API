# Use a base image with Java Runtime
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the jar file into the image
COPY target/grpc-crud-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the gRPC port (example: 9090)
EXPOSE 9090

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]