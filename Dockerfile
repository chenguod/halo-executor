FROM openjdk:8-jre-slim

ENV TZ=PRC
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

## 设置挂载点
VOLUME /tmp

## 复制并修改重命名
ADD  /target/xxl-job-executor-0.0.1-SNAPSHOT.jar xxlJobExecutor.jar

# 运行jar包
RUN sh -c 'touch /xxlJobExecutor.jar'

ENTRYPOINT ["java","-Xms512m","-Xmx512m","-jar","-Djava.security.egd=file:/dev/./urandom","/xxlJobExecutor.jar"]
