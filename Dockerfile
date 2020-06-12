#### Stage 1: Build the application
FROM gradle:6.2.2-jdk11 as builder

COPY --chown=gradle:gradle . /home/gradle/src

COPY application.properties.docker src/main/resources/application.properties

WORKDIR /home/gradle/src

RUN gradle build --no-daemon

#### Stage 2: Run the application
FROM openjdk:11-jre-slim

EXPOSE 8082

RUN mkdir /app

COPY --from=builder /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar

ENTRYPOINT ["java", "-jar", "/app/spring-boot-application.jar"]
