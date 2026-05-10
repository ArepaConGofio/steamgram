#!/bin/bash

gnome-terminal -- bash -c "cd src/steamgram && mvn clean install && mvn clean spring-boot:run; exec bash"
gnome-terminal -- bash -c "cd src/front-end && npm install && npm run start; exec bash"