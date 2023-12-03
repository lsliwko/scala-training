#!/bin/sh

DOCKER_IMAGE=docker.digital.homeoffice.gov.uk/scala/play-scala-kafka:${APP_VERSION}

echo "Building Play Stage..."
sbt clean stage

echo "Building Docker image..."
docker build . -t ${DOCKER_IMAGE}

echo "To run:"
echo docker container run -dp 9000:9000 -t ${DOCKER_IMAGE}
