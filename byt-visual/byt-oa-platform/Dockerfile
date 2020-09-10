FROM java:8-jre

MAINTAINER wangiegie@gmail.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /byt-oa-platform

WORKDIR /byt-oa-platform

EXPOSE 5005

ADD ./byt-visual/byt-oa-platform/target/byt-oa-platform.jar ./

CMD sleep 120;java -Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom -jar byt-oa-platform.jar
