# Use an appropriate base image
FROM openjdk:17-jdk-slim

# Set environment variables
ENV APP_HOME=/app
ENV LOG_DIR=/data/logs/backend-job-service

# Create application directories
RUN mkdir -p $APP_HOME
RUN mkdir -p $LOG_DIR

# Set working directory
WORKDIR $APP_HOME

# Copy application JAR file into the container
COPY target/jobs-0.0.1.jar backend-job-service.jar

# Set permissions for the log directory
RUN chown -R nobody:nogroup $LOG_DIR

# Expose application port
EXPOSE 8081

# Run the application
ENTRYPOINT ["java", "-jar", "backend-job-service.jar"]

# Optional: Use this to set the logging directory if your application supports it
# ENV LOGGING_DIRECTORY=$LOG_DIR