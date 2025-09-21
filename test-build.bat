@echo off
echo Testing Maven build...
mvn clean package -DskipTests

echo.
echo Checking for JAR file...
if exist "target\issue-tracker-1.0.0.jar" (
    echo ✓ JAR file found: target\issue-tracker-1.0.0.jar
    dir target\*.jar
) else (
    echo ✗ JAR file not found!
    echo Available files in target:
    dir target
)

echo.
echo Build test completed.
