FROM java:8-jre

MAINTAINER wangiegie@gmail.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /byt-monitor

WORKDIR /byt-monitor

EXPOSE 5001

ADD ./byt-visual/byt-monitor/target/byt-monitor.jar ./

CMD sleep 120;java -Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom -jar byt-monitor.jar
