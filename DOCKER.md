# CiviConnect API - Docker Setup

This guide explains how to run the CiviConnect API using Docker and Docker Compose with an external PostgreSQL database.

## Prerequisites

- Docker (version 20.10 or higher)
- Docker Compose (version 2.0 or higher)
- **External PostgreSQL Database** (accessible from your Docker host)

## Quick Start

### 1. Configure Environment Variables

Copy the example environment file and configure it with your settings:

```bash
cd civiconnect-api
cp .env.example .env
```

Edit the `.env` file with your actual configuration:

```bash
# External Database Configuration
# Replace with your external PostgreSQL database connection details
SPRING_DATASOURCE_URL=jdbc:postgresql://your-external-db-host:5432/civiconnect
POSTGRES_USER=postgres
POSTGRES_PASSWORD=your_secure_password_here

# API Configuration
API_PORT=8080

# Mail Configuration
MAIL_RECIPIENT=your_recipient_email@example.com
MAIL_HOST=smtp.gmail.com
MAIL_PORT=587
MAIL_USERNAME=your_email@gmail.com
MAIL_PASSWORD=your_app_specific_password

# JWT Configuration
JWT_SECRET=your_jwt_secret_key_here_minimum_256_bits_long_for_security
```

**Important**: The `SPRING_DATASOURCE_URL` must be a complete JDBC URL including:
- Host: Your external database server hostname or IP
- Port: Database port (usually 5432)
- Database name: The database name to connect to

Examples:
- Local network: `jdbc:postgresql://192.168.1.100:5432/civiconnect`
- Cloud database: `jdbc:postgresql://my-db.example.com:5432/civiconnect`
- Docker host: `jdbc:postgresql://host.docker.internal:5432/civiconnect` (for databases on the Docker host machine)

### 2. Prepare Your External Database

Ensure your external PostgreSQL database:
1. Is running and accessible from your Docker host
2. Has the database created (e.g., `civiconnect`)
3. Has the correct user permissions configured
4. Allows connections from the Docker container's network

Test the connection before starting Docker:
```bash
psql -h your-external-db-host -p 5432 -U postgres -d civiconnect
```

### 3. Start the Application

Run the following command to build and start the API:

```bash
docker-compose up -d
```

This will:
- Build the Spring Boot API Docker image
- Start the API container
- Connect to your external PostgreSQL database

### 4. Verify the Application is Running

Check the status of the container:

```bash
docker-compose ps
```

View the API logs:

```bash
docker-compose logs -f api
```

Access the API:
- API Base URL: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui.html
- API Docs: http://localhost:8080/api-docs

## Common Commands

### Stop the Application

```bash
docker-compose down
```

### Rebuild the API Image

If you make changes to the code:

```bash
docker-compose up -d --build api
```

### View Logs

```bash
docker-compose logs -f api
```

### Restart the API Service

```bash
docker-compose restart api
```

### Scale Services (if needed)

```bash
docker-compose up -d --scale api=3
```

## Database Setup

Since you're using an external PostgreSQL database, you need to ensure your database schema is created on the external server.

Connect to your external database and run your migration scripts:

```bash
psql -h your-external-db-host -p 5432 -U postgres -d civiconnect -f /path/to/your/schema.sql
```

Or connect to the database and run your scripts manually:

```bash
psql -h your-external-db-host -p 5432 -U postgres -d civiconnect
```

## Troubleshooting

### Port Already in Use

If port 8080 is already in use, change the port in your `.env` file:

```bash
API_PORT=8081
```

### Database Connection Issues

1. **Verify database is accessible**: Test connection from your host machine:
   ```bash
   psql -h your-external-db-host -p 5432 -U postgres -d civiconnect
   ```

2. **Check firewall rules**: Ensure your external database allows connections from the Docker container

3. **Verify database credentials**: Double-check the `SPRING_DATASOURCE_URL`, `POSTGRES_USER`, and `POSTGRES_PASSWORD` in `.env` file

4. **Check network connectivity**: If using `host.docker.internal`, ensure Docker Desktop is configured correctly

5. **Database host resolution**:
   - For databases on the same machine: Use `host.docker.internal` (Docker Desktop) or the host's IP address
   - For remote databases: Use the actual hostname or IP address

### API Not Starting

1. Check API logs for errors:
   ```bash
   docker-compose logs api
   ```

2. Ensure all required environment variables are set in `.env`

3. Verify the external database is accessible and running

4. Test database connection manually before starting the API

### Rebuild from Scratch

If you encounter persistent issues:

```bash
docker-compose down
docker-compose build --no-cache
docker-compose up -d
```

## Production Considerations

For production deployment:

1. **Use Strong Passwords**: Change all default passwords in `.env`
2. **Secure JWT Secret**: Use a strong, random JWT secret (minimum 256 bits)
3. **Environment Variables**: Never commit `.env` file to version control
4. **HTTPS**: Use a reverse proxy (nginx, traefik) with SSL certificates
5. **Database Backups**: Set up regular PostgreSQL backups on your external database
6. **Database Security**:
   - Use SSL/TLS for database connections
   - Restrict database access to specific IP addresses
   - Use strong database passwords
7. **Resource Limits**: Configure memory and CPU limits in docker-compose.yml
8. **Monitoring**: Add health checks and monitoring tools
9. **Logging**: Configure centralized logging (ELK stack, etc.)
10. **Network Security**: Ensure secure communication between API and external database

## Docker Compose Services

### API Service
- **Image**: Built from Dockerfile
- **Port**: 8080 (configurable via API_PORT)
- **Dependencies**: External PostgreSQL database (not managed by Docker Compose)
- **Volumes**: `./tokens` for Google Drive API credentials
- **Network**: Bridge mode for external connectivity

## Volumes

- `./tokens`: Mounted directory for Google Drive API tokens

## External Database Connection

The API connects to an external PostgreSQL database. The connection details are configured via environment variables:

- `SPRING_DATASOURCE_URL`: Full JDBC connection URL
- `POSTGRES_USER`: Database username
- `POSTGRES_PASSWORD`: Database password

**Network Considerations**:
- If the database is on the same host as Docker: Use `host.docker.internal` or the host's IP
- If the database is on a remote server: Use the server's hostname or IP address
- Ensure firewall rules allow connections from the Docker container

## Support

For issues or questions, please refer to the main project documentation or create an issue in the repository.