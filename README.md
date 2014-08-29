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

