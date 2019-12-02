FROM openjdk:8-alpine

ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/java-info.jar"]

ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/java-info.jar
