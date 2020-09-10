FROM java:8-jre

MAINTAINER wangiegie@gmail.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /byt-daemon-quartz

WORKDIR /byt-daemon-quartz

EXPOSE 5007

ADD ./byt-visual/byt-daemon-quartz/target/byt-daemon-quartz.jar ./

CMD sleep 120;java -Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom -jar byt-daemon-quartz.jar
