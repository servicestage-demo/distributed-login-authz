FROM openjdk:8-jre-alpine
ENV APP_ROOT=/root/servicestage/example/
ENV LOG_ROOT=/var/log/example/
RUN mkdir -p $APP_ROOT
RUN mkdir -p $LOG_ROOT

COPY target/example-1.0/example-1.0 $APP_ROOT


RUN cd $APP_ROOT && chmod -R 770 .
RUN chmod +x /root/servicestage/example/bin/start.sh

ENTRYPOINT ["sh", "/root/servicestage/example/bin/start.sh"]