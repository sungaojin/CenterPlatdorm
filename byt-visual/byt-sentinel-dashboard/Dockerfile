FROM java:8-jre

MAINTAINER wangiegie@gmail.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /byt-sentinel-dashboard

WORKDIR /byt-sentinel-dashboard

EXPOSE 5020

ADD ./byt-visual/byt-sentinel-dashboard/target/byt-sentinel-dashboard.jar ./

CMD sleep 120;java -Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom -jar byt-sentinel-dashboard.jar
