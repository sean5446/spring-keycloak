#!/usr/bin/env bash

keycloak_host="http://192.168.50.190:8180"
app_host="http://localhost:8080"
username="mike"
password="mike"
client_id="login-app"

token=$(curl -s --request POST \
  --url "$keycloak_host/realms/demo/protocol/openid-connect/token" \
  --header 'Authorization: Bearer ' \
  --header 'content-type: application/x-www-form-urlencoded' \
  --data client_id="$client_id" \
  --data username="$username" \
  --data password="$password" \
  --data grant_type=password | grep access_token | awk -F '"' '{print $4}')

echo "$token"

curl -w "%{http_code}" -s --request GET \
  --url "$app_host/products/json?limit=10&page=1" \
  --header "Authorization: Bearer $token" \
  --header 'content-type: application/x-www-form-urlencoded' \
  --data search=the | jq
