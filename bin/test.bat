@echo off
echo [INFO] Install project to local repository.
cd %~dp0
cd ../
call mvn test
pause