# Use an appropriate base image
FROM openjdk:17-jdk-slim
RUN ln -fs /usr/share/zoneinfo/Asia/Jakarta /etc/localtime && dpkg-reconfigure -f noninteractive tzdata
RUN mkdir -p /data/docker/backend-service
WORKDIR /data/docker/backend-service
COPY ./target/jobs-0.0.1.jar /data/docker/backend-service
ENV SERVICEPORT=8081


EXPOSE 8080
ENTRYPOINT ["java", "-jar",  "-Dserver.port=8081","jobs-0.0.1.jar"]
