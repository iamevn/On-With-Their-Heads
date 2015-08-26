FROM java:8
MAINTAINER gpwclark

# Update phusion and install necessary programs
RUN apt-get update && apt-get install -y \
  wget \
  libssl-dev \
  tmux \
  curl \
  ca-certificates \
  wamerican \
  maven 

# Make non sudo user and copy code from git repo to the docker image
RUN useradd -ms /bin/bash user
RUN mkdir -p /home/user/app
ADD . /home/user/app

# Install lein
WORKDIR /home/user/app
RUN wget https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein
RUN chmod a+x ./lein
RUN cp ./lein /usr/local/bin/lein

# Housekeeping
RUN chown -R user:user /home/user
RUN apt-get clean
RUN rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

USER user
RUN lein

# Compile clojure code
RUN ["bash", "buildjar.sh"]

# Spark set-up
RUN ["mvn", "dependency:resolve"]
RUN ["mvn", "verify"]
RUN ["mvn", "package"]


# Open port and set default cmd
EXPOSE 4567
#CMD ["/usr/lib/jvm/java-8-openjdk-amd64/bin/java", "-jar", "target/sparkproject-jar-with-dependencies.jar"]
