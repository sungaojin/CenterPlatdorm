FROM java:8-jre

MAINTAINER wangiegie@gmail.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /byt-register

WORKDIR /byt-register

EXPOSE 8848

ADD ./byt-register/target/byt-register.jar ./


CMD sleep 60;java -Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom -jar byt-register.jar


