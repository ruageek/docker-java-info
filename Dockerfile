FROM openjdk:8
MAINTAINER Thomas Leplus <thomas@leplus.org>

ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/java-info.jar"]

ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/java-info.jar
