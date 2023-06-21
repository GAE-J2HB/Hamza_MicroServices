FROM amazoncorretto:17
EXPOSE 8083
ENV DB_HOST=mysql-docker \
     DB_PORT=3306 \
     DB_NAME=J2HB \
     DB_USER=root \
     DB_PASS=ham147532@ \
     DEBUG=false \
     SERVER_PORT=8083 \
     AUTH_HOST=localhost \
     AUTH_PORT=8082 \
     EUREKA_HOST=localhost \
     EUREKA_PORT=8761
ADD ../configuration-service/target/configuration-0.0.1-SNAPSHOT.jar config-service.jar
ENTRYPOINT ["java", "-jar", "/auth.jar"]