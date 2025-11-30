# ---- Stage 1: Build the application ----
FROM gradle:8.5-jdk25 AS builder

WORKDIR /app

# Copy important Gradle files first for better caching
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# Copy application source
COPY src ./src

# Build Spring Boot JAR (skip tests)
RUN ./gradlew clean bootJar -x test --no-daemon


# ---- Stage 2: Runtime image ----
FROM eclipse-temurin:25-jre

WORKDIR /app

# Install curl for Docker healthcheck (Render needs this)
USER root
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# Create non-root user
RUN groupadd -r spring && useradd -r -g spring spring

# Copy built JAR from builder image
COPY --from=builder /app/build/libs/*.jar app.jar

# Create directory for tokens
RUN mkdir -p /app/tokens && chown -R spring:spring /app

# Switch to non-root user
USER spring

# Expose port (internal only)
EXPOSE 8080

# Bind to Render's PORT value (Render injects PORT env var)
ENV PORT=8080

# Health check (you said your health path is /api/health)
# IMPORTANT: Use Render's env PORT, not hardcoded 8080
HEALTHCHECK --interval=30s --timeout=5s --start-period=40s --retries=3 \
  CMD curl -f http://localhost:${PORT}/api/health || exit 1

# Run Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
