@echo off
echo [INFO] package the project.
cd %~dp0
cd ../
call mvn package
pause