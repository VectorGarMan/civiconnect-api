# ---- Stage 1: Build the application ----
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

# Copy Gradle config first (better caching)
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# Download dependencies (cached unless build.gradle changes)
RUN ./gradlew dependencies --no-daemon

# Copy the source code
COPY . .

# Build the Spring Boot jar without tests
RUN ./gradlew clean bootJar -x test --no-daemon


# ---- Stage 2: Run the Spring Boot application ----
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copy the JAR from builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose Spring Boot default port
EXPOSE 8080

# Start the app
ENTRYPOINT ["java", "-jar", "app.jar"]
