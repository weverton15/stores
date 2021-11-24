FROM openjdk:17-alpine
MAINTAINER weverton
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/stores-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} stores.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/stores.jar"]