FROM openjdk:8-jre-alpine
ENV APP_ROOT=/root/servicestage/edge/
ENV LOG_ROOT=/var/log/edge/
RUN mkdir -p $APP_ROOT
RUN mkdir -p $LOG_ROOT

COPY target/edge-1.0/edge-1.0 $APP_ROOT


RUN cd $APP_ROOT && chmod -R 770 .
RUN chmod +x /root/servicestage/edge/bin/start.sh

ENTRYPOINT ["sh", "/root/servicestage/edge/bin/start.sh"]