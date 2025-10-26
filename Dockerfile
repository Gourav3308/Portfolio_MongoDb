# Use Maven directly instead of wrapper
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the backend directory
COPY backend/ .

# Install Maven and build
RUN apt-get update && apt-get install -y maven
RUN mvn clean package -DskipTests

# Expose port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/portfolio-backend-0.0.1-SNAPSHOT.jar"]
