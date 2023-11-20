FROM amazoncorretto:11-alpine-jdk

ENV APP_HOME=/var/lib/shopping-cart

RUN mkdir -p ${APP_HOME}
WORKDIR ${APP_HOME}

#bash is required for Play stage scripts
RUN \
    apk update && \
    apk upgrade && \
    apk add bash

#copy Play stage assembly to app home folder
COPY target/universal/stage/ ${APP_HOME}/

#expose default Play port
EXPOSE 9000

#to generate Play secret: sbt playGenerateSecret
ENTRYPOINT bin/play-scala-shopping-cart -Dplay.http.secret.key=3Xz1ZXmNVeM9eA98NkxR
