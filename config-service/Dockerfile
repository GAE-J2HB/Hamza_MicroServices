FROM amazoncorretto:17
EXPOSE 8080
ADD target/auth-0.0.1-SNAPSHOT.jar auth.jar
ENTRYPOINT ["java", "-jar", "/auth.jar"]