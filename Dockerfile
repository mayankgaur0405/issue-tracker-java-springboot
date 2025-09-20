# Use OpenJDK 17 as base image
FROM openjdk:17-jdk-slim

# Install Maven and other dependencies
RUN apt-get update && \
    apt-get install -y maven curl && \
    rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app

# Copy pom.xml first for better caching
COPY pom.xml .

# Copy Maven wrapper
COPY mvnw .
COPY .mvn .mvn

# Make mvnw executable
RUN chmod +x mvnw

# Copy source code
COPY src src

# Build the application with verbose output
RUN mvn clean package -DskipTests -X

# Expose port
EXPOSE 8080

# Set environment variables
ENV SPRING_PROFILES_ACTIVE=production
ENV PORT=8080

# Run the application
CMD ["java", "-jar", "target/issue-tracker-0.0.1-SNAPSHOT.jar"]
