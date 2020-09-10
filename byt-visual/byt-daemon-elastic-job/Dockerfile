FROM java:8-jre

MAINTAINER wangiegie@gmail.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /byt-daemon-elastic-job

WORKDIR /byt-daemon-elastic-job

EXPOSE 5002

ADD ./byt-visual/byt-daemon-elastic-job/target/byt-daemon-elastic-job.jar ./

CMD sleep 120;java -Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom -jar byt-daemon-elastic-job.jar
