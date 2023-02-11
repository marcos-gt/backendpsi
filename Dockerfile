FROM maven:3.8.7-openjdk-18 AS build
COPY /home/marcos/IdeaProjects/psi/src
COPY /home/marcos/IdeaProjects/psi/src
# Exponha a porta na qual a aplicação será acessível
EXPOSE 8080