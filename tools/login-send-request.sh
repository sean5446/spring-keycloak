#!/usr/bin/env bash

token=$(curl -s -X POST \
  --url http://localhost:8180/realms/demo/protocol/openid-connect/token \
  --header 'Authorization: Bearer ' \
  --header 'content-type: application/x-www-form-urlencoded' \
  --data client_id=login-app \
  --data username=mike \
  --data password=mike \
  --data grant_type=password | grep access_token | awk -F '"' '{print $4}')

curl --request GET \
  --url 'http://localhost:8080/products/json?limit=10&page=1' \
  --header "Authorization: Bearer $token" \
  --header 'content-type: application/x-www-form-urlencoded' \
  --data search=the | jq
