FROM openjdk:8-jdk-alpine
ARG jar_file=project0816-0.0.1-SNAPSHOT.jar
COPY ${jar_file} project0816.jar
ENTRYPOINT ["java", "-jar", "/project0816.jar"]

