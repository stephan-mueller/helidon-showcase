FROM openjdk:11-jre-slim

RUN mkdir -p /opt/helidon

COPY target/helidon-showcase.jar /opt/helidon/helidon-showcase.jar
COPY target/libs /opt/helidon/libs

EXPOSE 8080
ENTRYPOINT exec java -jar /opt/helidon/helidon-showcase.jar
