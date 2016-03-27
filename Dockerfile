# Creates a docker image with the latest dary runnable
# Note that dary is NOT newly built from source when this image is build

FROM java:8-jre

MAINTAINER David Rieger
LABEL version 0.1

RUN mkdir /opt/dary
COPY target/universal/stage /opt/dary
COPY servicestart.sh /opt/dary
WORKDIR /opt/dary

EXPOSE 9000

ENTRYPOINT /opt/dary/bin/dary
