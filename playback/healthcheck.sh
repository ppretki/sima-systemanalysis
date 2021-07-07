#!/bin/sh

response_status_code=$(wget -S -q localhost:8082/actuator/health 2>&1 | head -1 | awk '{print $2}')
if [ "$response_status_code" = '503' ]
  then exit 1
fi
exit 0
