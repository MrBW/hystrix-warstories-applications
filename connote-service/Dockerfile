FROM java:openjdk-8u72-jdk

MAINTAINER Benjamin Wilms <benjamin.wilms@codecentric.de>

ENV ETCD_SERIVCE http://etcd:2379/v2/keys/hystrix

ENV SERVICE_NAME connote-service
ENV SERVICE_HOME /opt/$SERVICE_NAME
ENV SOURCE_JAR_FILENAME target/connote-service-0.0.1-SNAPSHOT.jar
ENV DEST_JAR_FILENAME connote-service.jar

EXPOSE 8081

WORKDIR $SERVICE_HOME
COPY run.sh $SERVICE_HOME/bin/
RUN chmod +x $SERVICE_HOME/bin/run.sh

COPY $SOURCE_JAR_FILENAME $SERVICE_HOME/bin/$DEST_JAR_FILENAME
RUN chmod +x $SERVICE_HOME/bin/$DEST_JAR_FILENAME

ENTRYPOINT ["./bin/run.sh"]
