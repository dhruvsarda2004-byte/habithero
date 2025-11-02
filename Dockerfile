# ===============================
# Stage 1: Build the Spring Boot app
# ===============================
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app

# Copy only pom.xml first (to cache dependencies)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the source code
COPY src ./src

# Build the application inside the container
RUN mvn clean package -DskipTests

# ===============================
# Stage 2: Run the app
# ===============================
FROM openjdk:21-jdk-slim
WORKDIR /app

# Copy the built jar from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose Spring Boot's default port
EXPOSE 8080

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
