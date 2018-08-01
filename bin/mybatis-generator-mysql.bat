@echo off
@echo [INFO] mybatis generator mysql start...
cd %~dp0
cd ../
call  mvn -Dmybatis.generator.configurationFile=./bin/mybatis-generator-mysql.xml mybatis-generator:generate
@echo [INFO] mybatis generator mysql completed!
pause