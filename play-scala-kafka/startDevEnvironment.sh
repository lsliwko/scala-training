#!/usr/bin/env bash

echo "Building Play Stage..."
sbt clean stage

# create .env (for docker-compose) with dynamic variables
cat > .env <<-EOPROP
# This is the environment variables config file for docker-compose
EOPROP

# reads Dockerfile and .env
docker-compose up