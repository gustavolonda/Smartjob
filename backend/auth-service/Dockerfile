FROM openjdk:17-jdk-alpine as builder
WORKDIR /app/auth-service
COPY ./auth-service   /app/auth-service
RUN  /app/auth-service/gradlew clean build && cp -f  /app/auth-service/build/libs/auth-service-0.0.1-SNAPSHOT.jar /app/auth-service-0.0.1-SNAPSHOT.jar
run  rm -r /app/auth-service

FROM openjdk:17-jdk-alpine

WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/auth-service-0.0.1-SNAPSHOT.jar .
EXPOSE 8095

CMD  java -jar auth-service-0.0.1-SNAPSHOT.jar