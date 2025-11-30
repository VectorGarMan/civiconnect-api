# CiviConnect API

Spring Boot REST API for the CiviConnect civic engagement platform.

## Prerequisites

- Java 21
- Gradle 8.x (included via Gradle Wrapper)
- PostgreSQL database

## Building the Project

### Build JAR File

To generate the executable JAR file with all dependencies:

```bash
# Make gradlew executable (first time only)
chmod +x gradlew

# Build the JAR file
./gradlew bootJar
```

The JAR file will be generated at:
```
build/libs/civiconnect-api.jar
```

### Full Build (includes tests)

```bash
./gradlew build
```

This will:
- Compile the source code
- Run all tests
- Generate the JAR file

### Clean Build

To clean previous builds and rebuild from scratch:

```bash
./gradlew clean build
```

## Running the Application

### Run from JAR

After building the JAR file:

```bash
java -jar build/libs/civiconnect-api.jar
```

### Run with Gradle

```bash
./gradlew bootRun
```

### Run with Docker

See [DOCKER.md](DOCKER.md) for detailed Docker setup instructions.

## Configuration

### Local Development

1. Copy the example properties file:
   ```bash
   cp src/main/resources/application.properties.example src/main/resources/application.properties
   ```

2. Edit `application.properties` with your database and mail configuration.

### Docker Deployment

1. Copy the example environment file:
   ```bash
   cp .env.example .env
   ```

2. Edit `.env` with your configuration.

3. Start with Docker Compose:
   ```bash
   docker-compose up -d
   ```

## API Documentation

Once the application is running, access the API documentation at:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI Spec**: http://localhost:8080/api-docs

## Project Structure

```
civiconnect-api/
├── src/
│   ├── main/
│   │   ├── java/com/vectorgarman/civiconnect/
│   │   │   ├── config/          # Configuration classes
│   │   │   ├── controller/      # REST controllers
│   │   │   ├── dto/             # Data Transfer Objects
│   │   │   ├── entity/          # JPA entities
│   │   │   ├── repository/      # Data repositories
│   │   │   ├── service/         # Business logic
│   │   │   └── template/        # Email templates
│   │   └── resources/
│   │       └── application.properties
│   └── test/                    # Test files
├── build.gradle                 # Gradle build configuration
├── docker-compose.yml          # Docker Compose configuration
├── Dockerfile                  # Docker image definition
└── README.md                   # This file
```

## Development

### Common Gradle Tasks

```bash
# Compile the project
./gradlew compileJava

# Run tests
./gradlew test

# Check for dependency updates
./gradlew dependencyUpdates

# View all available tasks
./gradlew tasks
```

### IDE Setup

#### IntelliJ IDEA
1. Open the project directory
2. IntelliJ will automatically detect the Gradle project
3. Wait for dependencies to download

#### VS Code
1. Install the "Extension Pack for Java"
2. Open the project directory
3. VS Code will detect the Gradle project

## Troubleshooting

### Permission Denied on gradlew

If you get a "permission denied" error:

```bash
chmod +x gradlew
```

### Port Already in Use

If port 8080 is already in use, change it in `application.properties`:

```properties
server.port=8081
```

### Database Connection Issues

1. Verify PostgreSQL is running
2. Check database credentials in `application.properties`
3. Ensure the database exists

## Additional Documentation

- [Docker Setup Guide](DOCKER.md) - Detailed Docker deployment instructions

## Support

For issues or questions, please create an issue in the repository.