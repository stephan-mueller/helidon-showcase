FROM openjdk:11-jre-slim

RUN apt-get update && apt-get install -y curl

RUN mkdir -p /opt/helidon

COPY target/helidon-showcase.jar /opt/helidon/helidon-showcase.jar
COPY target/libs /opt/helidon/libs

HEALTHCHECK --start-period=10s --timeout=60s --retries=10 --interval=5s CMD curl -f http://localhost:8080/health/ready || exit 1

EXPOSE 8080
ENTRYPOINT exec java -jar /opt/helidon/helidon-showcase.jar
