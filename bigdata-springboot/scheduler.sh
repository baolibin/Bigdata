#!/usr/bin/env bash

APP_Name="bigdata-springboot-0.0.1-SNAPSHOT.jar"

# 判断应用程序是否已启动
# 1：程序已启动
# 0：程序未启动
isExist(){
    echo "判断程序是否在运行..."
    pid=`ps -ef|grep ${APP_Name}|grep -v "grep"|awk '{print $2}'`
    if [ -z ${pid} ];then
        echo ${pid}
        return 0
    else
        echo ${pid}
        return 1
    fi
}

# 启动应用程序
start(){
    echo "springboot app start..."
    isExist
    if [ $? -eq "1" ];then
        echo "程序已启动,pid = ${pid}"
    else
        echo "程序未启动"
        nohup java -jar target/${APP_Name} com.libin.bigdata.BigDataApplication > scheduler.log 2>&1 &
    fi
}


stop(){
    echo "停止正在运行的应用程序..."
    isExist
    if [ $? -eq "1" ];then
        kill -9 ${pid}
        echo "${APP_Name} 的进程号 ${pid} 已杀死..."
    else
        echo "${APP_Name} is not running..."
    fi

}


status(){
    echo "查看应用程序运行状态..."
    isExist
    if [ $? -eq "1" ];then
        echo "${APP_Name} 的进程号 ${pid} is running..."
    else
        echo "${APP_Name} is not running..."
    fi

}

restart(){
    start
    stop
}

start
#status
#stop
status
