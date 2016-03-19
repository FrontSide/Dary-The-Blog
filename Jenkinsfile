// This Pipeline builds a Dary from source, creates a docker image containing
// the runnable and pushes the source code to github if successful

node {

   stage 'Checkout Source'
   git url: 'http://github.com/frontside/dary-the-blog.git',
       branch: 'v0.2.7-BETA-dockerize'

   // Build Dary from Source
   stage 'Build Dary from Source'
   docker run --rm -v $(pwd):/app ingensi/play-framework /bin/bash -c "activator clean stage"

   stage 'Build Docker Image'
   docker build -t dary --no-cache .

   stage ""
   // Get some code from a GitHub repository


   // Get the maven tool.
   // ** NOTE: This 'M3' maven tool must be configured
   // **       in the global configuration.
   def mvnHome = tool 'M3'

   // Mark the code build 'stage'....
   stage 'Build'
   // Run the maven build
   sh "${mvnHome}/bin/mvn clean install"
}
