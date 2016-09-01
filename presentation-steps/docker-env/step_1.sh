#!/bin/bash

baseurl=http://0.0.0.0:2379/v2/keys/hystrix

# Default Service URLs
clear
echo +++ Step 1 - Chaos Monkey +++
echo
echo chaos.monkey.active = true
echo
curl -L -X PUT $baseurl/chaos.monkey.active -d value="true"
echo
