FROM amazoncorretto:17
EXPOSE 8080
ENV DB_HOST=mysql-docker \
     DB_PORT=3306 \
     DB_USER=root \
     DB_PASS=ham147532@ \
     DB_NAME=j2hb \
     AUTH_HOST=localhost \
     AUTH_PORT=8082 \
     EXAM_HOST=localhost \
     EXAM_PORT=8081 \
     COURSE_HOST=localhost \
     COURSE_PORT=9002 \
     CANDIDATE_HOST=localhost \
     CANDIDATE_PORT=9001 \
     CONFIG_HOST=localhost \
     CONFIG_PORT=8083 \
     EUREKA_HOST=localhost \
     EUREKA_PORT=8761 \
     GATEWAY_PORT=8080 \
     DEBUG=false
ADD target/j2hb-gateway-0.0.1-SNAPSHOT.jar j2hb-gateway.jar
ENTRYPOINT ["java", "-jar", "/j2hb-gateway.jar"]