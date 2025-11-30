# ---- Stage 1: Build with Java 25 ----
FROM eclipse-temurin:25-jdk AS builder

WORKDIR /app

# Install Gradle manually
ARG GRADLE_VERSION=8.7
RUN apt-get update && apt-get install -y wget unzip && \
    wget https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip && \
    unzip gradle-${GRADLE_VERSION}-bin.zip -d /opt && \
    ln -s /opt/gradle-${GRADLE_VERSION}/bin/gradle /usr/bin/gradle && \
    rm gradle-${GRADLE_VERSION}-bin.zip

# Copy project files
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle
COPY src ./src

# Build JAR with Java 25 + Gradle
RUN gradle clean bootJar -x test --no-daemon


# ---- Stage 2: Runtime Java 25 ----
FROM eclipse-temurin:25-jre

WORKDIR /app

# Install curl for health checks
USER root
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# Create non-root user
RUN groupadd -r spring && useradd -r -g spring spring

# Copy the built JAR from builder
COPY --from=builder /app/build/libs/*.jar app.jar

# Tokens directory (optional)
RUN mkdir -p /app/tokens && chown -R spring:spring /app

USER spring

EXPOSE 8080
ENV PORT=8080

# Health Check (your endpoint)
HEALTHCHECK --interval=30s --timeout=5s --start-period=40s --retries=3 \
  CMD curl -f http://localhost:${PORT}/api/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]
