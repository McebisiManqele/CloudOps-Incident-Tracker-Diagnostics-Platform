package com.cloudops.incidents.dto;

import com.cloudops.incidents.model.ErrorType;
import com.cloudops.incidents.model.Severity;
import com.cloudops.incidents.model.IncidentStatus;
import java.time.Instant;

/**
 * Data Transfer Object (DTO) for returning incident information via the API.
 * This represents the complete incident data that gets sent back to API clients.
 * 
 * This class contains all incident information including:
 * - System-generated fields (id, timestamps, status)
 * - User-provided fields (title, description, severity, etc.)
 * 
 * The API returns this object when:
 * - Creating a new incident (POST /api/incidents)
 * - Getting incident details (GET /api/incidents/{id})
 * - Updating an incident (PUT /api/incidents/{id})
 * - Listing all incidents (GET /api/incidents)
 * 
 * Example JSON response:
 * {
 *   "id": "INC-2024-001",
 *   "title": "Payment API Down",
 *   "description": "All payment requests returning 500 errors",
 *   "severity": "CRITICAL",
 *   "status": "INVESTIGATING",
 *   "createdAt": "2024-01-15T14:30:00Z",
 *   "updatedAt": "2024-01-15T14:35:00Z",
 *   "serviceName": "payment-processor",
 *   "errorType": "NETWORK",
 *   "correlationId": "req-abc-123"
 * }
 */
public class IncidentResponse {
    
    /** Unique identifier for this incident */
    private String id;
    
    /** Short, descriptive title of the incident */
    private String title;
    
    /** Detailed description of what went wrong */
    private String description;
    
    /** How serious this incident is */
    private Severity severity;
    
    /** Current stage of incident resolution */
    private IncidentStatus status;
    
    /** When this incident was first created (UTC) */
    private Instant createdAt;
    
    /** When this incident was last modified (UTC) */
    private Instant updatedAt;
    
    /** Which service or component failed */
    private String serviceName;
    
    /** What type of failure occurred */
    private ErrorType errorType;
    
    /** ID for linking related events across services */
    private String correlationId;

    /** Default constructor required by Spring Boot for JSON serialization */
    public IncidentResponse() {}

    // Getter and setter methods for JSON conversion
    
    /** Gets the unique incident ID */
    public String getId() { return id; }
    /** Sets the unique incident ID */
    public void setId(String id) { this.id = id; }

    /** Gets the incident title */
    public String getTitle() { return title; }
    /** Sets the incident title */
    public void setTitle(String title) { this.title = title; }

    /** Gets the incident description */
    public String getDescription() { return description; }
    /** Sets the incident description */
    public void setDescription(String description) { this.description = description; }

    /** Gets the severity level */
    public Severity getSeverity() { return severity; }
    /** Sets the severity level */
    public void setSeverity(Severity severity) { this.severity = severity; }

    /** Gets the current status */
    public IncidentStatus getStatus() { return status; }
    /** Sets the current status */
    public void setStatus(IncidentStatus status) { this.status = status; }

    /** Gets when the incident was created */
    public Instant getCreatedAt() { return createdAt; }
    /** Sets when the incident was created */
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    /** Gets when the incident was last updated */
    public Instant getUpdatedAt() { return updatedAt; }
    /** Sets when the incident was last updated */
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }

    /** Gets the service name */
    public String getServiceName() { return serviceName; }
    /** Sets the service name */
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }

    /** Gets the error type */
    public ErrorType getErrorType() { return errorType; }
    /** Sets the error type */
    public void setErrorType(ErrorType errorType) { this.errorType = errorType; }

    /** Gets the correlation ID */
    public String getCorrelationId() { return correlationId; }
    /** Sets the correlation ID */
    public void setCorrelationId(String correlationId) { this.correlationId = correlationId; }
}