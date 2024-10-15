# Use an official openjdk image as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from your machine to the container
COPY target/*.war /app/commerce.war

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/postgres

# Expose the application port
EXPOSE 8081

# Set the entry point to run the Spring Boot app
ENTRYPOINT ["java", "-jar", "commerce.war"]