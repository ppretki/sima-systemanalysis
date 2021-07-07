#!/bin/sh
mvn clean install -DskipTests -DskipITs
docker build -t playback:1.0.0 .