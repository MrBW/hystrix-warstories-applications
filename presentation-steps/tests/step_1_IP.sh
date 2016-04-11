#!/bin/bash

baseurl=http://192.168.99.100:2379/v2/keys/hystrix

# Default Service URLs
echo +++ Step 1 - Connect Services +++
echo
curl -L -X PUT $baseurl/service.address.customer -d value="http://192.168.99.100:8082/customer/find/accountnumber/"
echo
curl -L -X PUT $baseurl/service.address.connote -d value="http://192.168.99.100:8081/connote/create"
echo
