FROM java:8-jre

MAINTAINER wangiegie@gmail.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /byt-pay-platform

WORKDIR /byt-mp-platform

EXPOSE 6000

ADD ./byt-visual/byt-mp-platform/target/byt-mp-platform.jar ./

CMD sleep 120;java -Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom -jar byt-mp-platform.jar
