#!/bin/bash

baseurl=http://127.0.0.1:2379/v2/keys/hystrix

# Default Service URLs
clear
echo +++ Step 2 - Connect Remote Services +++
echo
echo service.address.customer = http://customer-service/customer/find/accountnumber/
echo service.address.connote  = http://connote-service/connote/create/
echo
curl -L -X PUT $baseurl/service.address.customer -d value="http://customer-service/customer/find/accountnumber/"
echo
curl -L -X PUT $baseurl/service.address.connote -d value="http://connote-service/connote/create/"
echo
