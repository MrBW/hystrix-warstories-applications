#!/bin/bash

baseurl=http://192.168.99.100:2379/v2/keys/hystrix

# Default Service URLs
clear
echo +++ Step 2 - Connect Remote Services +++
echo
curl -L -X PUT $baseurl/service.address.customer -d value="http://customer-service:8080/customer/find/accountnumber/"
echo
curl -L -X PUT $baseurl/service.address.connote -d value="http://connote-service:8080/connote/create/"
echo
