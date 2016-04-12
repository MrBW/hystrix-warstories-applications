#!/bin/bash

baseurl=http://192.168.99.100:2379/v2/keys/hystrix

# Default Service URLs
clear
echo +++ Step 3 - Transport Hystrix Remote Service Timeouts +++
echo
curl -L -X PUT $baseurl/hystrix.command.ConnoteClientCmdKey.execution.isolation.thread.timeoutInMilliseconds -d value="3000"
curl -L -X PUT $baseurl/hystrix.command.CustomerClientCmdKey.execution.isolation.thread.timeoutInMilliseconds -d value="3000"
echo
