FROM java:8-jre

MAINTAINER wangiegie@gmail.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /byt-pay-platform

WORKDIR /byt-pay-platform

EXPOSE 5010

ADD ./byt-visual/byt-pay-platform/target/byt-pay-platform.jar ./

CMD sleep 120;java -Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom -jar byt-pay-platform.jar
