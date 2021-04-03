FROM openjdk:13-jdk-alpine

COPY build/libs/*.jar /root/

WORKDIR /root

CMD java -jar -Djava.security.egd=file:/dev/./urandom $(ls | grep jar)