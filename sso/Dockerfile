FROM openjdk:8-jre-alpine
ENV APP_ROOT=/root/servicestage/sso/
ENV LOG_ROOT=/var/log/sso/
RUN mkdir -p $APP_ROOT
RUN mkdir -p $LOG_ROOT

COPY target/sso-1.0/sso-1.0 $APP_ROOT


RUN cd $APP_ROOT && chmod -R 770 .
RUN chmod +x /root/servicestage/sso/bin/start.sh

ENTRYPOINT ["/root/servicestage/sso/bin/start.sh"]