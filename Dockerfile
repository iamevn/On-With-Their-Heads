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
RUN lein

# 
RUN chown -R user:user /home/user


# Spark set-up
RUN ["mvn", "dependency:resolve"]
RUN ["mvn", "verify"]
RUn ["mvn", "package"]

# Make the container leaner
RUN apt-get clean
RUN rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

# Open port and set default cmd
EXPOSE 4567
CMD ["/usr/lib/jvm/java-8-openjdk-amd64/bin/java", "-jar", "target/sparkexample-jar-with-dependencies.jar"]
