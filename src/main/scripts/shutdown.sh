#!/bin/bash
count=`ps -ef|grep app_name|grep -v grep|wc -l`
if [ $count -gt 0 ];then
	onsale_pid=`ps -ef | grep app_name |grep -v grep| awk '{print $2}'`
	kill $onsale_pid
	echo "OnSale Listener shutdowned!"
else
    echo "app_name dosen't exists!"
fi