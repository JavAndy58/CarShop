FROM openjdk:17-jdk-alpine
VOLUME /tmp
EXPOSE 5432
ARG JAR_FILE=target/CarShop-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} carshop.jar
ENTRYPOINT ["java", "-jar","/carshop.jar"]




