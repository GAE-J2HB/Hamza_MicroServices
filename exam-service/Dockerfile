FROM amazoncorretto:17
EXPOSE 8081
ENV DB_HOST=mysql-docker \
     DB_PORT=3306 \
     DB_USER=root \
     DB_PASS=ham147532@ \
     DB_NAME=J2HB \
     DEBUG=true \
     SERVER_PORT=8081 \
     AUTH_HOST=localhost \
     AUTH_PORT=8082 \
     EUREKA_HOST=localhost \
     EUREKA_PORT=8761
ADD target/exam-managment-0.0.1-SNAPSHOT.jar exam-managment.jar
ENTRYPOINT ["java", "-jar", "/exam-managment.jar"]