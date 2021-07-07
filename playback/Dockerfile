FROM adoptopenjdk/openjdk11:jre-11.0.7_10-alpine

RUN addgroup -S -g 1000 sima && adduser -u 1000 -S sima -G sima

COPY docker-entrypoint.sh /home/sima/docker-entrypoint.sh
COPY healthcheck.sh /home/sima/healthcheck.sh
COPY target/playback-1.0.0.jar /home/sima/playback.jar

RUN chown -R sima:sima /home/sima

USER sima

HEALTHCHECK CMD "/home/sima/healthcheck.sh"

EXPOSE 8080

CMD ["/home/sima/docker-entrypoint.sh"]
