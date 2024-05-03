FROM eclipse-temurin:17-jdk-alpine
COPY target/project1-0.0.1-SNAPSHOT.jar project1-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/project1-0.0.1-SNAPSHOT.jar"]