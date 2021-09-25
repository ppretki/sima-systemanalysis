#!/bin/bash

trap "exit" INT TERM ERR
trap "kill 0" EXIT

export $(cat .env | xargs) && locust -f test_playback.py --headless -u 1 -r 1 --run-time 60s &

#export $(cat .env | xargs) && locust -f test_playback.py -u 10 -r 1

wait
