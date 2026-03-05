# CloudOps Incident Tracker

A comprehensive incident management system for cloud operations teams to track, manage, and resolve infrastructure incidents efficiently.

## Overview

The CloudOps Incident Tracker provides a centralized platform for managing cloud infrastructure incidents with real-time diagnostics, automated data collection, and comprehensive reporting capabilities.

## Current Status

✅ **Phase 1**: Core data model with CloudOps fields (COMPLETE)
✅ **Phase 2**: REST API layer with full documentation (COMPLETE)
✅ **Phase 3**: In-memory data persistence (COMPLETE)
🚧 **Phase 4**: Input validation and error handling (IN PROGRESS)
⏳ **Phase 5**: Database persistence (SQLite/DynamoDB)
⏳ **Phase 6**: Frontend dashboard
⏳ **Phase 7**: AWS deployment

## Architecture

- **Backend**: Java Spring Boot API for incident management
- **Data Storage**: In-memory (development) → SQLite (local) → DynamoDB (production)
- **Diagnostics**: Node.js service for automated data collection
- **Frontend**: Web dashboard for incident visualization
- **Infrastructure**: AWS SAM templates for deployment

## Features

### ✅ Implemented
- ✅ Full CRUD operations for incidents
- ✅ RESTful API with proper HTTP methods
- ✅ CloudOps-specific fields (serviceName, errorType, correlationId)
- ✅ Severity levels (LOW, MEDIUM, HIGH, CRITICAL)
- ✅ Status tracking (OPEN, INVESTIGATING, MITIGATED, RESOLVED)
- ✅ Error type classification (NETWORK, APPLICATION, CONFIGURATION, RESOURCE)
- ✅ UTC timestamp handling
- ✅ Thread-safe in-memory storage
- ✅ Input validation with clear error messages
- ✅ Comprehensive documentation

### 🚧 In Progress
- 🚧 Enhanced error handling
- 🚧 API documentation (Swagger/OpenAPI)

### ⏳ Planned
- ⏳ Database persistence
- ⏳ Automated diagnostics collection
- ⏳ Web-based dashboard
- ⏳ CloudWatch integration
- ⏳ AWS deployment automation

## Quick Start

### Prerequisites
- Java 11 or higher
- Maven 3.6+
- curl (for testing)

### Running the Application

1. **Navigate to the backend directory**
   ```bash
   cd backend/incident-api
   ```

2. **Start the application**
   ```bash
   mvn spring-boot:run
   ```

3. **Verify it's running**
   ```bash
   curl http://localhost:8080/health
   # Expected: OK
   ```

### Quick Test

**Create an incident:**
```bash
curl -X POST http://localhost:8080/api/incidents \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Payment API Down",
    "description": "All payment requests returning 500 errors",
    "severity": "CRITICAL",
    "serviceName": "payment-processor",
    "errorType": "NETWORK",
    "correlationId": "req-abc-123"
  }'
```

**List all incidents:**
```bash
curl http://localhost:8080/api/incidents
```

**Get specific incident:**
```bash
curl http://localhost:8080/api/incidents/{id}
# Replace {id} with actual incident ID from create response
```

## Project Structure

```
CloudOps-Incident-Tracker-Diagnostics-Platform/
├── backend/
│   ├── incident-api/              # Java Spring Boot API
│   │   ├── src/main/java/
│   │   │   └── com/cloudops/incidents/
│   │   │       ├── controller/      # REST API endpoints
│   │   │       ├── service/         # Business logic
│   │   │       ├── repository/      # Data access
│   │   │       ├── model/           # Domain entities
│   │   │       ├── dto/             # API request/response
│   │   │       ├── exception/       # Error handling
│   │   │       └── util/            # Utilities
│   │   ├── src/main/resources/
│   │   │   ├── application.yml  # App configuration
│   │   │   └── logback.xml      # Logging config
│   │   └── pom.xml              # Maven dependencies
│   └── diagnostics-ingestor/  # Node.js diagnostics (planned)
├── frontend/                  # Web dashboard (planned)
├── infrastructure/            # AWS SAM templates (planned)
├── docs/                      # Documentation
│   ├── PHASE_2_COMPLETE.md    # REST API guide
│   ├── PHASE_3_COMPLETE.md    # Testing guide
│   └── VALIDATION_TESTING.md  # Validation tests
├── QUICKSTART.md              # Quick setup guide
└── README.md                  # This file
```

## API Endpoints

### Health Check
- `GET /health` - Check if API is running

### Incident Management
- `GET /api/incidents` - List all incidents
- `POST /api/incidents` - Create new incident
- `GET /api/incidents/{id}` - Get specific incident
- `PUT /api/incidents/{id}` - Update incident
- `DELETE /api/incidents/{id}` - Delete incident

### Request Validation

**Required Fields:**
- `title` (1-200 characters)
- `serviceName` (1-100 characters)
- `severity` (LOW, MEDIUM, HIGH, CRITICAL)
- `errorType` (NETWORK, APPLICATION, CONFIGURATION, RESOURCE)

**Optional Fields:**
- `description` (max 1000 characters)
- `correlationId` (max 100 characters)

**Example Valid Request:**
```json
{
  "title": "Payment API Down",
  "description": "All payment requests failing",
  "severity": "CRITICAL",
  "serviceName": "payment-processor",
  "errorType": "NETWORK",
  "correlationId": "req-abc-123"
}
```

**Example Response:**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "title": "Payment API Down",
  "description": "All payment requests failing",
  "severity": "CRITICAL",
  "status": "OPEN",
  "serviceName": "payment-processor",
  "errorType": "NETWORK",
  "correlationId": "req-abc-123",
  "createdAt": "2024-01-15T14:30:00Z",
  "updatedAt": "2024-01-15T14:30:00Z"
}
```

## Documentation

- **[QUICKSTART.md](QUICKSTART.md)** - Get started in 5 minutes
- **[docs/PHASE_2_COMPLETE.md](docs/PHASE_2_COMPLETE.md)** - REST API documentation
- **[docs/PHASE_3_COMPLETE.md](docs/PHASE_3_COMPLETE.md)** - Complete testing guide with 10 scenarios
- **[docs/VALIDATION_TESTING.md](docs/VALIDATION_TESTING.md)** - Input validation testing

## Testing

### Manual Testing with curl

See [docs/PHASE_3_COMPLETE.md](docs/PHASE_3_COMPLETE.md) for comprehensive testing guide.

### Validation Testing

See [docs/VALIDATION_TESTING.md](docs/VALIDATION_TESTING.md) for validation scenarios.

### Quick Validation Test

**Valid request (should work):**
```bash
curl -X POST http://localhost:8080/api/incidents \
  -H "Content-Type: application/json" \
  -d '{"title":"Test","serviceName":"test","severity":"LOW","errorType":"APPLICATION"}'
```

**Invalid request (should fail):**
```bash
curl -X POST http://localhost:8080/api/incidents \
  -H "Content-Type: application/json" \
  -d '{"serviceName":"test","severity":"LOW","errorType":"APPLICATION"}'
# Missing title - returns 400 Bad Request
```

## Troubleshooting

### Port Already in Use
```bash
# Find process using port 8080
lsof -i :8080

# Kill the process
kill -9 <PID>
```

### Application Won't Start
```bash
# Check Java version (needs 11+)
java -version

# Clean and rebuild
mvn clean install
```

### Data Lost After Restart
This is expected - currently using in-memory storage.
Data persistence (SQLite/DynamoDB) coming in Phase 5.

## Technology Stack

- **Java 11** - Programming language
- **Spring Boot 2.7.0** - Application framework
- **Maven** - Build tool
- **Logback** - Logging
- **Bean Validation** - Input validation
- **In-Memory Storage** - Current data layer (temporary)

## Development Roadmap

### Phase 1: Core Data Model ✅
- CloudOps-specific fields
- Type-safe enums
- UTC timestamp handling

### Phase 2: REST API Layer ✅
- Full CRUD operations
- Proper HTTP methods
- Error handling

### Phase 3: Data Persistence ✅
- In-memory storage
- Thread-safe operations
- Repository pattern

### Phase 4: Validation & Error Handling 🚧
- Input validation (DONE)
- Enhanced error messages (IN PROGRESS)
- API documentation (PLANNED)

### Phase 5: Database Integration ⏳
- SQLite for local development
- DynamoDB for production
- Data migration tools

### Phase 6: Frontend Dashboard ⏳
- Incident list view
- Real-time updates
- Filtering and search

### Phase 7: AWS Deployment ⏳
- Lambda functions
- API Gateway
- CloudWatch integration