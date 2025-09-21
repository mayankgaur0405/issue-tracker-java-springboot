#!/bin/bash
echo "Starting Java build process..."
echo "Java version:"
java -version
echo "Maven version:"
mvn -version
echo "Building application..."
mvn clean package -DskipTests
echo "Build completed. Checking for JAR file..."
ls -la target/
echo "JAR files found:"
find target/ -name "*.jar" -type f
