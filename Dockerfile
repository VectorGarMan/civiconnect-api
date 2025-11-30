# ----------------------------
# 1. Build Stage
# ----------------------------
FROM eclipse-temurin:25-jdk AS builder

WORKDIR /app

# Install Gradle
RUN apt-get update && apt-get install -y wget unzip && \
    wget https://services.gradle.org/distributions/gradle-8.7-bin.zip && \
    unzip gradle-8.7-bin.zip -d /opt && \
    ln -s /opt/gradle-8.7/bin/gradle /usr/bin/gradle && \
    rm gradle-8.7-bin.zip

# Copy project files
COPY . .

# Build JAR
RUN gradle clean build --no-daemon

# ----------------------------
# 2. Runtime Stage
# ----------------------------
FROM eclipse-temurin:25-jre

WORKDIR /app

# Copy JAR from builder
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
