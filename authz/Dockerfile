FROM openjdk:8-jre-alpine
ENV APP_ROOT=/root/servicestage/authz/
ENV LOG_ROOT=/var/log/authz/
RUN mkdir -p $APP_ROOT
RUN mkdir -p $LOG_ROOT

COPY target/authz-1.0/authz-1.0 $APP_ROOT


RUN cd $APP_ROOT && chmod -R 770 .
RUN chmod +x /root/servicestage/authz/bin/start.sh

ENTRYPOINT ["/root/servicestage/authz/bin/start.sh"]