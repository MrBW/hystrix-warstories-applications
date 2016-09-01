#!/bin/bash

baseurl=http://0.0.0.0:2379/v2/keys/hystrix

# Default Service URLs
clear
echo +++ Step 5 - Transport Hystrix Remote Service Fallbacks - enabled = false +++
echo
echo hystrix.command.ConnoteServiceCmdKey.fallback.enabled = false
echo hystrix.command.CustomerServiceCmdKey.fallback.enabled = false
echo
curl -L -X PUT $baseurl/hystrix.command.ConnoteServiceCmdKey.fallback.enabled -d value="false"
curl -L -X PUT $baseurl/hystrix.command.CustomerServiceCmdKey.fallback.enabled -d value="false"
echo
