package com.cloudops.incidents.controller;

import com.cloudops.incidents.dto.IncidentRequest;
import com.cloudops.incidents.dto.IncidentResponse;
import com.cloudops.incidents.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST API Controller for incident management operations.
 * This is the entry point for all HTTP requests related to incidents.
 * 
 * Base URL: /api/incidents
 * 
 * Available endpoints:
 * - GET    /api/incidents        - List all incidents
 * - GET    /api/incidents/{id}   - Get specific incident details
 * - POST   /api/incidents        - Create new incident
 * - PUT    /api/incidents/{id}   - Update existing incident
 * - DELETE /api/incidents/{id}   - Delete incident
 * 
 * This controller follows REST principles:
 * - Uses HTTP methods correctly (GET for read, POST for create, etc.)
 * - Returns proper HTTP status codes (200 OK, 204 No Content, etc.)
 * - Uses JSON for request and response bodies
 * - Resource-based URLs (operates on "incidents" resource)
 */
@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    /**
     * Service layer that handles business logic.
     * Spring automatically injects this dependency.
     */
    @Autowired
    private IncidentService incidentService;

    /**
     * GET /api/incidents
     * 
     * Retrieves all incidents in the system.
     * 
     * Use cases:
     * - Dashboard displaying all active incidents
     * - Reporting and analytics
     * - Incident history review
     * 
     * Example request:
     * GET http://localhost:8080/api/incidents
     * 
     * Example response:
     * [
     *   {
     *     "id": "INC-001",
     *     "title": "Payment API Down",
     *     "severity": "CRITICAL",
     *     "status": "OPEN",
     *     ...
     *   }
     * ]
     * 
     * @return HTTP 200 OK with list of all incidents
     */
    @GetMapping
    public ResponseEntity<List<IncidentResponse>> getAllIncidents() {
        return ResponseEntity.ok(incidentService.getAllIncidents());
    }

    /**
     * GET /api/incidents/{id}
     * 
     * Retrieves a specific incident by its unique ID.
     * 
     * Use cases:
     * - Viewing incident details
     * - Checking incident status
     * - Getting incident history
     * 
     * Example request:
     * GET http://localhost:8080/api/incidents/INC-001
     * 
     * Example response:
     * {
     *   "id": "INC-001",
     *   "title": "Payment API Down",
     *   "severity": "CRITICAL",
     *   "status": "INVESTIGATING",
     *   "serviceName": "payment-processor",
     *   "errorType": "NETWORK",
     *   "createdAt": "2024-01-15T14:30:00Z",
     *   "updatedAt": "2024-01-15T14:35:00Z"
     * }
     * 
     * @param id The unique identifier of the incident
     * @return HTTP 200 OK with incident details
     */
    @GetMapping("/{id}")
    public ResponseEntity<IncidentResponse> getIncident(@PathVariable String id) {
        return ResponseEntity.ok(incidentService.getIncident(id));
    }

    /**
     * POST /api/incidents
     * 
     * Creates a new incident in the system.
     * 
     * Use cases:
     * - Automated monitoring systems reporting failures
     * - Manual incident creation by engineers
     * - Integration with alerting tools
     * 
     * The system automatically:
     * - Generates unique incident ID
     * - Sets status to OPEN
     * - Records creation timestamp
     * 
     * Example request:
     * POST http://localhost:8080/api/incidents
     * Content-Type: application/json
     * 
     * {
     *   "title": "Payment API Down",
     *   "description": "All payment requests returning 500 errors",
     *   "severity": "CRITICAL",
     *   "serviceName": "payment-processor",
     *   "errorType": "NETWORK",
     *   "correlationId": "req-abc-123"
     * }
     * 
     * Example response:
     * {
     *   "id": "550e8400-e29b-41d4-a716-446655440000",
     *   "status": "OPEN",
     *   "createdAt": "2024-01-15T14:30:00Z",
     *   ... (all other fields from request)
     * }
     * 
     * @param request The incident data from the client
     * @return HTTP 200 OK with the created incident (including generated ID)
     */
    @PostMapping
    public ResponseEntity<IncidentResponse> createIncident(@RequestBody IncidentRequest request) {
        return ResponseEntity.ok(incidentService.createIncident(request));
    }

    /**
     * PUT /api/incidents/{id}
     * 
     * Updates an existing incident with new information.
     * 
     * Use cases:
     * - Adding more details as investigation progresses
     * - Correcting incident information
     * - Updating severity as impact becomes clear
     * 
     * Note: This does NOT change:
     * - Incident ID (immutable)
     * - Creation timestamp (historical record)
     * - Status (use separate endpoint for status transitions)
     * 
     * Example request:
     * PUT http://localhost:8080/api/incidents/INC-001
     * Content-Type: application/json
     * 
     * {
     *   "title": "Payment API Down - Database Connection Issue",
     *   "description": "Root cause: Database connection pool exhausted",
     *   "severity": "CRITICAL",
     *   "serviceName": "payment-processor",
     *   "errorType": "RESOURCE",
     *   "correlationId": "req-abc-123"
     * }
     * 
     * @param id The ID of the incident to update
     * @param request The new incident data
     * @return HTTP 200 OK with the updated incident
     */
    @PutMapping("/{id}")
    public ResponseEntity<IncidentResponse> updateIncident(@PathVariable String id, @RequestBody IncidentRequest request) {
        return ResponseEntity.ok(incidentService.updateIncident(id, request));
    }

    /**
     * DELETE /api/incidents/{id}
     * 
     * Deletes an incident from the system.
     * 
     * Use cases:
     * - Removing duplicate incidents
     * - Deleting test incidents
     * - Cleaning up false alarms
     * 
     * Warning: This permanently removes the incident.
     * Consider using status changes instead for production incidents.
     * 
     * Example request:
     * DELETE http://localhost:8080/api/incidents/INC-001
     * 
     * Example response:
     * HTTP 204 No Content (empty body)
     * 
     * @param id The ID of the incident to delete
     * @return HTTP 204 No Content (successful deletion with no response body)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncident(@PathVariable String id) {
        incidentService.deleteIncident(id);
        return ResponseEntity.noContent().build();
    }
}