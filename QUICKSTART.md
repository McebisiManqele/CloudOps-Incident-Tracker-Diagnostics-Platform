# Quick Start Guide - CloudOps Incident Tracker

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- curl (for testing)

## Running the Application

### Option 1: Using Maven
```bash
cd backend/incident-api
mvn spring-boot:run
```

### Option 2: Using IDE
1. Open project in IntelliJ IDEA or Eclipse
2. Navigate to `Application.java`
3. Right-click → Run

### Option 3: Build and Run JAR
```bash
cd backend/incident-api
mvn clean package
java -jar target/incident-tracker-1.0.0.jar
```

## Verify It's Running

```bash
curl http://localhost:8080/health
```

Expected: `OK`

## Quick Test

### Create an incident:
```bash
curl -X POST http://localhost:8080/api/incidents \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Test Incident",
    "severity": "LOW",
    "serviceName": "test-service",
    "errorType": "APPLICATION"
  }'
```

### List all incidents:
```bash
curl http://localhost:8080/api/incidents
```

## API Endpoints

- `GET /health` - Health check
- `GET /api/incidents` - List all incidents
- `GET /api/incidents/{id}` - Get specific incident
- `POST /api/incidents` - Create incident
- `PUT /api/incidents/{id}` - Update incident
- `DELETE /api/incidents/{id}` - Delete incident

## Full Documentation

See `docs/PHASE_3_COMPLETE.md` for comprehensive testing guide.

## Troubleshooting

**Port already in use:**
```bash
# Change port in application.yml
server:
  port: 8081
```

**Maven build fails:**
```bash
mvn clean install -U
```

**Application won't start:**
- Check Java version: `java -version`
- Check Maven version: `mvn -version`
- Review console logs for errors
