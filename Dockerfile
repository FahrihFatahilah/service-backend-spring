# Use an appropriate base image
FROM openjdk:17-jdk-slim
RUN mkdir -p /data/docker/backend-service
WORKDIR /data/docker/backend-service
ENV SERVICEPORT=8081
EXPOSE 8080
ENTRYPOINT ["java", "-jar",  "-Dserver.port=8081","backend-job-service.jar"]
