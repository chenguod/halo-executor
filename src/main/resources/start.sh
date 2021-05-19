#!/bin/sh
NUM_1=`docker ps -a | grep xxlJobExecutor | grep -vi grep |wc -l`
echo "容器是否存活:******************"${NUM_1}"******************"
NUM_2=`docker images | grep xxlJobExecutor | grep -v grep | wc -l`
echo "镜像是否存在:******************"${NUM_2}"******************"
if [[ "${NUM_2}"!="0" ]];then
     echo "---------镜像存在！！！，判断容器是否存活-----------"
     if [[ "${NUM_1}"!="0" ]];then
         echo "---------容器存活，停止容器并删除容器---------"
        docker stop xxlJobExecutor
        docker rm xxlJobExecutor
     fi
    echo "--------删除镜像---------"
   docker rmi xxlJobExecutor
fi
echo "----------开始构建镜像......--------------"
docker build -t xxlJobExecutor:latest /home/xxl-job-executor/
echo "---------运行容器------------"
docker run -dit --name xxlJobExecutor -p 8083:8083 -v /home/xxl-job-executor/logs:/home/xxl-job-executor/logs xxlJobExecutor:latest
