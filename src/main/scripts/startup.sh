#!/bin/bash
# app_name
count=`ps -ef|grep app_name|grep -v grep|wc -l`
if [ $count -gt 0 ];then
echo "app_name is already exists!"
exit
fi
apps_home=`dirname $0`/..
log_path=`pwd`/log/app_name.log
cp=$apps_home/etc/:$apps_home/lib/*
nohup java -Xmx256M  -cp "$cp" com.kevin.Startup app_name 1>/dev/null 2>&1 &
echo "log path:"$log_path
echo "app_name started!"