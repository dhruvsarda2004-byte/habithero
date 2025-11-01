# Step 1: Use base image
FROM openjdk:21-jdk-slim

# Step 2: Set working directory inside container
WORKDIR /app

# Step 3: Copy the built jar into the container
COPY target/habithero-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Expose port 8080 (Spring Boot default)
EXPOSE 8080

# Step 5: Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
