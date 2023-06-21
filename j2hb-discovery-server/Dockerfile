FROM amazoncorretto:17
EXPOSE 8761
ENV DEBUG=false
ADD target/j2hb-discovery-server-0.0.1-SNAPSHOT.jar j2hb-discovery-server.jar
ENTRYPOINT ["java", "-jar", "/j2hb-discovery-server.jar"]