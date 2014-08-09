# Dary-The-Blog

A Blogging Web-App written in Java using the Framework Play.
Official Website : [www.dary.info] (http://www.dary.info)

## Features

- Picture Upload (directly into Article)

Features in development

- Featured Posts (Display as Buttons)
- View Counter
- Commenting
- User Settings (Profile Picture)
- Article Version Control *not before v1.2*

## Dependencies and Third-Party Software

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

## Technologies

- [Play! Framework 2.2.1] (http://www.playframework.com)
  This is a super cool Web-App-Framework for Java and Scala.

- [PostgreSQL 9.1] (http://www.postgresql.org/)
  Relational Database
  *Maybe Transition to NoSQL in the near future*

## Development

v0.1.0-BETA
This version is hostet on Heroku and is not maintained 
[www.dary.info] (http://www.dary.info)

v0.1.1-BETA
This is the public BETA that is consequently updated
[Official BETA] (http://www.letsdoarnie.com)

