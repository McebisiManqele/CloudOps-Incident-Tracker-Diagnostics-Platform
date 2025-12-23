# CloudOps Incident Tracker

A comprehensive incident management system for cloud operations teams to track, manage, and resolve infrastructure incidents efficiently.

## Overview

The CloudOps Incident Tracker provides a centralized platform for managing cloud infrastructure incidents with real-time diagnostics, automated data collection, and comprehensive reporting capabilities.

## Architecture

- **Backend**: Java Spring Boot API for incident management
- **Diagnostics**: Node.js service for automated data collection
- **Frontend**: Web dashboard for incident visualization
- **Infrastructure**: AWS SAM templates for deployment
- **Database**: DynamoDB for scalable data storage

## Features

- Real-time incident tracking and management
- Automated diagnostics collection
- Severity-based incident classification
- RESTful API for integration
- Web-based dashboard
- CloudWatch integration for monitoring

## Quick Start

1. **Prerequisites**
   - AWS CLI configured
   - Docker and Docker Compose
   - Java 11+
   - Node.js 16+

2. **Local Development**
   ```bash
   make setup
   make run-local
   ```

3. **Deploy to AWS**
   ```bash
   make deploy
   ```

## Project Structure

- `backend/` - Java API and Node.js diagnostics services
- `frontend/` - Web dashboard
- `infrastructure/` - AWS SAM templates and configurations
- `docs/` - Architecture documentation and runbooks
- `tests/` - Integration and load tests
- `scripts/` - Utility scripts for setup and maintenance

## API Endpoints

- `GET /api/incidents` - List incidents
- `POST /api/incidents` - Create incident
- `GET /api/incidents/{id}` - Get incident details
- `PUT /api/incidents/{id}` - Update incident
- `DELETE /api/incidents/{id}` - Delete incident

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Run tests: `make test`
5. Submit a pull request

## License

MIT License - see LICENSE file for details