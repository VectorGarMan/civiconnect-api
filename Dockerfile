# Multi-stage build for Spring Boot application

# Stage 1: Build the application
FROM gradle:8.5-jdk25 AS builder

# Set working directory
WORKDIR /app

# Copy gradle files
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# Copy source code
COPY src ./src

# Build the application (skip tests for faster builds)
RUN ./gradlew clean bootJar -x test --no-daemon

# Stage 2: Create the runtime image
FROM eclipse-temurin:25-jre

# Set working directory
WORKDIR /app

# Create a non-root user
RUN groupadd -r spring && useradd -r -g spring spring

# Copy the built jar from builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Create directory for tokens (Google Drive API)
RUN mkdir -p /app/tokens && chown -R spring:spring /app

# Switch to non-root user
USER spring

# Expose the application port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]