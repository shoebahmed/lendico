FROM openjdk:8u212-jdk-slim
LABEL maintainer="shoebahmed@yahoo.com"
VOLUME /tmp
EXPOSE 8080
ADD target/lendico-loan-0.0.1-SNAPSHOT.jar lendico-loan.jar
ENTRYPOINT ["java","-jar","/lendico-loan.jar"]