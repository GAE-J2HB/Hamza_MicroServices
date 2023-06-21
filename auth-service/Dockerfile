FROM amazoncorretto:17
EXPOSE 8083
ENV DB_HOST=mysql-docker \
     DB_PORT=3306 \
     DB_NAME=J2HB \
     DB_USER=root \
     DB_PASS=ham147532@ \
     DEBUG=false \
     SERVER_PORT=8083 \
     UPLOAD_PATH=/home/darkseid/Pictures/Test/ \
     EUREKA_HOST=localhost \
     EUREKA_PORT=8761
ADD target/auth-0.0.1-SNAPSHOT.jar auth-service.jar
ENTRYPOINT ["java", "-jar", "/auth-service.jar"]