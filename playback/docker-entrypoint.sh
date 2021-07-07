#!/bin/sh

if [[ "x$JAVA_MEM" = "x" ]]; then
  JVM_OPTS="-Xms128m -Xmx128m"
else
  JVM_OPTS="-Xms$JAVA_MEM -Xmx$JAVA_MEM"
fi

java $JVM_OPTS -XshowSettings:VM -XX:NativeMemoryTracking=summary -jar /home/sima/playback.jar