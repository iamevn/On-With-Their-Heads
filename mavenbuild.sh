#!/bin/bash

mvn install:install-file \
-Dfile=libs/wordgame/wordgame/0.0.1/wordgame-0.0.1-standalone.jar \
-DgroupId=wordgame \
-DartifactId=wordgame \
-Dversion=0.0.1 \
-Dpackaging=jar \
-DgeneratePom=true

mvn dependency:resolve
mvn verify
mvn package
