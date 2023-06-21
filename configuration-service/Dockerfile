FROM amazoncorretto:17
EXPOSE 8082
ENV DB_HOST=mysql-docker \
     DB_PORT=3306 \
     DB_USER=root \
     DB_PASS=ham147532@ \
     DB_NAME=J2HB \
     DEBUG=true \
     UPLOAD_PATH=/home/darkseid/Pictures/Testing/ \
     SERVER_PORT=8082 \
     EUREKA_HOST=localhost \
     EUREKA_PORT=8761
ADD target/auth-0.0.1-SNAPSHOT.jar auth.jar
ENTRYPOINT ["java", "-jar", "/auth.jar"]