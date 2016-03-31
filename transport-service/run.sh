#!/bin/bash
set -e

if [ "${1:0:1}" != '-' ]; then
	exec "$@"
fi

exec java -jar $SERVICE_HOME/bin/$DEST_JAR_FILENAME
	"$@"
