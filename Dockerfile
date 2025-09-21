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

# Copy Maven wrapper if it exists, otherwise use system Maven
COPY mvnw* ./
COPY .mvn .mvn 2>/dev/null || true

# Make mvnw executable if it exists
RUN if [ -f mvnw ]; then chmod +x mvnw; fi

# Copy source code
COPY src src

# Build the application with verbose output
RUN if [ -f mvnw ]; then ./mvnw clean package -DskipTests -X; else mvn clean package -DskipTests -X; fi

# Verify the JAR file was created and show contents
RUN ls -la target/ && \
    echo "Checking for JAR files:" && \
    find target/ -name "*.jar" -type f

# Expose port
EXPOSE 8080

# Set environment variables
ENV SPRING_PROFILES_ACTIVE=production
ENV PORT=8080

# Run the application
CMD ["java", "-jar", "target/issue-tracker-1.0.0.jar"]
