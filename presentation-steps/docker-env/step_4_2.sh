#!/bin/bash

baseurl=http://0.0.0.0:2379/v2/keys/hystrix

# Default Service URLs
clear
echo +++ Step 4 - Transport Hystrix Remote Service Circuit Breaker - forceOpen = false +++
echo
echo hystrix.command.ConnoteClientCmdKey.circuitBreaker.forceOpen = false
echo hystrix.command.CustomerClientCmdKey.circuitBreaker.forceOpen = false
echo
curl -L -X PUT $baseurl/hystrix.command.ConnoteClientCmdKey.circuitBreaker.forceOpen -d value="false"
curl -L -X PUT $baseurl/hystrix.command.CustomerClientCmdKey.circuitBreaker.forceOpen -d value="false"
echo
