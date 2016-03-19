// Creates a docker image with the latest dary runnable
// Note that dary is NOT newly built from source when this image is build

MAINTAINER David Rieger
VERSION 0.1

FROM java:8-jre

RUN mkdir /opt/dary
COPY target/universal/stage /opt/dary
WORKDIR /opt/dary

EXPOSE 9000

ENTRYPOINT /opt/dary/bin/dary
