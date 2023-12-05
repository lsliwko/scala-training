#!/usr/bin/env bash

# reads build.sbt
echo "Getting version..."
APP_VERSION=`sbt -Dsbt.supershell=false -error "print version"`
echo "Building version ${APP_VERSION}"


echo "Building Play Stage..."
sbt clean stage

# create .env (for docker-compose) with dynamic variables
cat > .env <<-EOPROP
# This is the environment variables config file for docker-compose
APP_VERSION=${APP_VERSION}
EOPROP

# reads Dockerfile and .env
docker-compose up --build --force-recreate --no-deps