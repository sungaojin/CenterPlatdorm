FROM java:8-jre

MAINTAINER wangiegie@gmail.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /byt-tx-manager

WORKDIR /byt-tx-manager

EXPOSE 5004
EXPOSE 9998

ADD ./byt-visual/byt-tx-manager/target/byt-tx-manager.jar ./

CMD sleep 120;java -Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom -jar byt-tx-manager.jar
