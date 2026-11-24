@echo off
setlocal
set DIR=%~dp0
set WRAPPER_JAR=%DIR%.mvn\wrapper\maven-wrapper.jar
if not exist "%WRAPPER_JAR%" (
    echo Downloading Maven Wrapper...
    powershell -NoProfile -ExecutionPolicy Bypass -Command "Invoke-WebRequest -UseBasicParsing 'https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.3.4/maven-wrapper-3.3.4.jar' -OutFile '%WRAPPER_JAR%'"
    if errorlevel 1 exit /b 1
)
java -jar "%WRAPPER_JAR%" %*
endlocal
