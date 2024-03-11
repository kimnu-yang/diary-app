FROM openjdk:17

LABEL maintainer="kiukangg@gmail.com"

EXPOSE 8080

ARG JAR_FILE=api/build/libs/*.jar

ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:dev/./urandom", "-jar","/app.jar"]