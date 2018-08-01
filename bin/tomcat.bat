@echo off
echo [INFO] Use maven tomcat-plugin run the project.
cd %~dp0
cd ../
set MAVEN_OPTS=%MAVEN_OPTS% -XX:MaxPermSize=128m
call mvn tomcat:run -Djetty.port=9090

pause