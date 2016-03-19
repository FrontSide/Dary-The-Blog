[![Stories in Ready](https://badge.waffle.io/frontside/dary-the-blog.png?label=ready&title=Ready)](https://waffle.io/frontside/dary-the-blog)
[![Build Status](https://travis-ci.org/FrontSide/Dary-The-Blog.svg?branch=master)](https://travis-ci.org/FrontSide/Dary-The-Blog)

# Dary-The-Blog

A Blogging Web-App written in Java using the Framework Play.
Official Website: [www.dary.info] (http://www.dary.info)

Currently available in English and German.

## Features

- Picture Upload (directly into Article)
- Featured Posts (Display as Buttons)
- Profile Picture Upload
- Commenting

Features in development

- View Counter
- User Settings
- Article Version Control *not before v1.2*

## Run with Docker

The easiest way to run dary is to do so within docker containers.
Run

    docker-compose up -d

in the repository root folder. This will download the latest dary docker image from the docker hub,
and run both the application as well as a postgresql database within docker containers.

There is also a Dockerfile, which puts the latest binaries and libraries into a docker image in case you want to use your own build.

## Build from Source

To build dary from source you need play 2.3 installed on your computer (or do it within a docker container - see farther down).
You can eiter simply:

    activator run

which makes dary available on your localhost on port 9000.
Or you can pack everyting up with

    activator clean stage

which will put everything inside the target/universial/stage folder,
including a file called dary inside the bin folder with which the app can be run.
Note however that you also need a database (check out the "Run with Docker" section)

If you want to build within a docker container (so that you don't need to install play),
run the following in the repository's root container:

    docker run --rm -v $(pwd):/app ingensi/play-framework /bin/bash -c "activator clean stage"

As mentioned, the resulting binaries will be located in target/universial/stage.
Also, check out the Jenkinsfile which does all of that automatically.


## Not included Dependencies and Third-Party Software

Some vendors are not included in the git repository.
Add the following libraries and third-party plugins in order to successfully run the App.

- [markdown-js] (https://github.com/evilstreak/markdown-js)
  This is the Markdown-to-HTML converter library.
  Copy the **markdown.min.js** file into the **/public/vendors/markdown/addons** folder.

  Dary uses the [bootstrap-markdown] (http://toopay.github.io/bootstrap-markdown/) editor, however,
  it is (and some of its dependencies are) included via CDNs.

- [to-markdown] (https://github.com/domchristie/to-markdown)
  This converts HTML back to Markdown (used for editing).
  Copy the **to-markdown.js** file into the **/public/vendors/markdown/addons** folder.

- ~~[Polymer] (http://www.polymer-project.org)
  This is a Google UI-Element Library.
  Install its core- and paper-elements into the **/public/vendors/polymer** folder
  preferably by using Twitter's package manager **Bower*~~

## Libraries

The following libraries (additionally to those that are already used by the Framework or
any Third-Party Software) are included in the Project (mostly via Ivy Dependencies, check out build.sbt)

- [Apache Commons Codec] (http://commons.apache.org/proper/commons-codec/)

- [Apache Commons IO] (http://commons.apache.org/proper/commons-io/)

- [JsMessage] (https://github.com/julienrf/play-jsmessages)


## Technologies

- [Play! Framework 2.3.0] (http://www.playframework.com)
  This is a super cool Web-App-Framework for Java and Scala.

- [PostgreSQL 9.1] (http://www.postgresql.org/)
  Relational Database
  *Maybe Transition to NoSQL in the near future*

## Development

v0.2.6-BETA
This is the public BETA that is consequently updated
[www.dary.info] (http://www.dary.info)
