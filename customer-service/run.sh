#!/bin/bash
echo check etcd service status
while ! curl http://192.168.99.100:2379/v2/keys/hystrix
do
  echo "$(date) - still trying"
  sleep 1
done
echo "$(date) - connected successfully"

set -e

if [ "${1:0:1}" != '-' ]; then
	exec "$@"
fi

exec java -jar $SERVICE_HOME/bin/$DEST_JAR_FILENAME
	"$@"
