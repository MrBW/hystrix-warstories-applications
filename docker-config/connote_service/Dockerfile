FROM java:openjdk-8u72-jdk

MAINTAINER Benjamin Wilms <benjamin.wilms@codecentric.de>

ENV SERVICE_NAME connote-service
ENV SERVICE_HOME /opt/$SERVICE_NAME

EXPOSE 8080

WORKDIR $SERVICE_HOME
COPY run.sh $SERVICE_HOME/bin/
RUN chmod +x $SERVICE_HOME/bin/run.sh

COPY $SERVICE_NAME.jar $SERVICE_HOME/bin/$SERVICE_NAME.jar
RUN chmod +x $SERVICE_HOME/bin/$SERVICE_NAME.jar

ENTRYPOINT ["./bin/run.sh"]
