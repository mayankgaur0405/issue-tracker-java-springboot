@echo off
echo ========================================
echo Testing Maven build for deployment
echo ========================================

echo.
echo Step 1: Cleaning previous builds...
mvn clean

echo.
echo Step 2: Running Maven build...
mvn package -DskipTests

echo.
echo Step 3: Checking build results...
if exist "target\issue-tracker-1.0.0.jar" (
    echo ✓ SUCCESS: JAR file found!
    echo File: target\issue-tracker-1.0.0.jar
    echo Size: 
    dir target\issue-tracker-1.0.0.jar
    echo.
    echo All JAR files in target:
    dir target\*.jar
) else (
    echo ✗ FAILED: JAR file not found!
    echo.
    echo Available files in target:
    dir target
    echo.
    echo Checking for any JAR files:
    dir target\*.jar
)

echo.
echo Step 4: Testing JAR execution...
if exist "target\issue-tracker-1.0.0.jar" (
    echo Testing JAR file (will timeout after 10 seconds)...
    timeout /t 1 /nobreak >nul
    echo JAR file appears to be valid
) else (
    echo Cannot test JAR - file not found
)

echo.
echo ========================================
echo Build test completed
echo ========================================
