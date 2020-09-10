FROM mysql:8.0.18

MAINTAINER liyang(wangiegie@gmail.com)

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

COPY ./db/1schema.sql /docker-entrypoint-initdb.d

COPY ./db/2byt.sql /docker-entrypoint-initdb.d

COPY ./db/3byt_ac.sql /docker-entrypoint-initdb.d

COPY ./db/4byt_job.sql /docker-entrypoint-initdb.d

COPY ./db/5byt_mp.sql /docker-entrypoint-initdb.d

COPY ./db/6byt_mes_config.sql /docker-entrypoint-initdb.d

COPY ./db/7byt_pay.sql /docker-entrypoint-initdb.d

COPY ./db/8byt_codegen.sql /docker-entrypoint-initdb.d
