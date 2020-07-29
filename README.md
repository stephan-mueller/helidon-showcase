# Helidon Showcase

[![GitHub last commit](https://img.shields.io/github/last-commit/stephan-mueller/helidon-showcase)](https://github.com/stephan-mueller/helidon-showcase/commits) 
[![GitHub](https://img.shields.io/github/license/stephan-mueller/helidon-showcase)](https://github.com/stephan-mueller/helidon-showcase/blob/master/LICENSE)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=stephan-mueller_helidon-showcase&metric=alert_status)](https://sonarcloud.io/dashboard?id=stephan-mueller_helidon-showcase)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=stephan-mueller_helidon-showcase&metric=coverage)](https://sonarcloud.io/dashboard?id=stephan-mueller_helidon-showcase)

This is a showcase for the microservice framework [Helidon v2](https://helidon.io). It contains a hello world application, 
which demonstrates several features of Helidon and Eclipse Microprofile.

Software requirements to run the samples are `maven`, `openjdk-11` (or any other JDK 11) and `docker`. When running the Maven lifecycle 
it will create the war package and use the `helidon-maven-plugin` to create a runnable jar (fat jar) which contains the application 
and the Jetty container. The fat jar will be copied into a Docker image using Spotify's `dockerfile-maven-plugin` during the package phase.

**Notable Features:**
* Integration of MP Health, MP Metrics and MP OpenAPI
* Testcontainer-Tests with Rest-Assured, Cucumber and Postman/newman
* Code-Coverage for Testcontainer-Tests
* [Sonarcloud](https://sonarcloud.io) Integration


## How to run

Before running the application it needs to be compiled and packaged using `Maven`. It creates the required jar and Docker image and can be 
run via `docker`:

```shell script
$ mvn clean package
$ docker run --rm -p 8080:8080 helidon-showcase
```

If everything works you can access the OpenAPI UI via http://localhost:8080/swagger-ui.

### Resolving issues

Sometimes it may happen that the containers did not stop as expected when trying to stop the pipeline early. This may
result in running containers although they should have been stopped and removed. To detect them you need to check
Docker:

```shell script
$ docker ps -a | grep helidon-showcase
```

If there are containers remaining although the application has been stopped you can remove them:

```shell script
$ docker rm <ids of the containers>
```


## Features

### Application 

The application is a very simple "Hello World" greeting service. It supports GET requests for generating a greeting message, and a PUT 
request for changing the greeting itself. The response is encoded using JSON.

Try the application
```shell script
curl -X GET http://localhost:8080/api/greet
{"message":"Hello World!"}

curl -X GET http://localhost:8080/api/greet/Stephan
{"message":"Hello Stephan!"}

curl -X PUT -H "Content-Type: application/json" -d '{"greeting" : "Hola"}' http://localhost:8080/api/greet/greeting

curl -X GET http://localhost:8080/api/greet/greeting
{"greeting":"Hola"}

curl -X GET http://localhost:8080/api/greet/Max
{"message":"Hola Max!"}
```

### Health, Metrics and OpenAPI

The application server provides built-in support for health, metrics and openapi endpoints.

Health liveness and readiness
```shell script
curl -s -X GET http://localhost:8080/health

curl -s -X GET http://localhost:8080/health/live

curl -s -X GET http://localhost:8080/health/ready
```

Metrics in Prometheus / JSON Format
```shell script
curl -s -X GET http://localhost:8080/metrics

curl -H 'Accept: application/json' -X GET http://localhost:8080/metrics
```

OpenAPI in YAML / JSON Format
```shell script
curl -s -X GET http://localhost:8080/openapi

curl -H 'Accept: application/json' -X GET http://localhost:8080/openapi
```