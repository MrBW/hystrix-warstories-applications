#!/bin/bash

baseurl=http://192.168.99.100:2379/v2/keys/hystrix

# Default Service URLs
clear
echo +++ Step 4 - Transport Hystrix Remote Service Circuit Breaker - forceOpen = true +++
clear
echo +++ Step 4 - Transport Hystrix Remote Service Circuit Breaker - forceOpen = true +++
echo
echo hystrix.command.ConnoteClientCmdKey.circuitBreaker.forceOpen = true
echo hystrix.command.CustomerClientCmdKey.circuitBreaker.forceOpen = true
echo
curl -L -X PUT $baseurl/hystrix.command.ConnoteClientCmdKey.circuitBreaker.forceOpen -d value="true"
curl -L -X PUT $baseurl/hystrix.command.CustomerClientCmdKey.circuitBreaker.forceOpen -d value="true"
echo
