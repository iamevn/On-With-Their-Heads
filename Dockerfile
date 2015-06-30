FROM phusion/baseimage
MAINTAINER gpwclark

RUN apt-get update && apt-get install -y \
  wget \
  libssl-dev \
  tmux \
  curl \
  ca-certificates \
  default-jdk
RUN wget https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein
RUN chmod a+x ./lein
RUN cp ./lein /usr/local/bin/lein

RUN useradd -ms /bin/bash blankout
RUN mkdir -p /home/blankout/app
ADD . /home/blankout/app

RUN chown -R blankout:blankout /home/blankout
RUN wget https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein
RUN chmod a+x ./lein
RUN cp ./lein /usr/local/bin/lein

WORKDIR /home/blankout/app
RUN lein
