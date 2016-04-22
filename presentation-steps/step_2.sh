#!/bin/bash

baseurl=http://192.168.99.100:2379/v2/keys/hystrix

# Default Service URLs
clear
echo +++ Step 2 - Connect Remote Services +++
echo
echo service.address.customer = localhost:8082/customer/find/accountnumber/
echo service.address.connote  = http://localhost:8081/connote/create/
echo
curl -L -X PUT $baseurl/service.address.customer -d value="http://localhost:8082/customer/find/accountnumber/"
echo
curl -L -X PUT $baseurl/service.address.connote -d value="http://localhost:8081/connote/create/"
echo
