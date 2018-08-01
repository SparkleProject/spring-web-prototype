@echo off
@echo [INFO] mybatis generator oracle start...
cd %~dp0
cd ../
call call mvn -Dmybatis.generator.configurationFile=./src/main/resources/mybatis-generator-oracle.xml mybatis-generator:generate
@echo [INFO] mybatis generator oracle completed!
pause