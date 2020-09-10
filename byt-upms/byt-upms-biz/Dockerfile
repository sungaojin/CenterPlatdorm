FROM java:8-jre

MAINTAINER wangiegie@gmail.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /byt-upms

WORKDIR /byt-upms

EXPOSE 4000

ADD ./byt-upms/byt-upms-biz/target/byt-upms-biz.jar ./

CMD sleep 120;java -Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom -jar byt-upms-biz.jar
