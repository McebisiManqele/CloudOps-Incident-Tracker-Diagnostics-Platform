package com.cloudops.incidents.model;

import java.time.Instant;

/**
 * Represents a single incident in the CloudOps system.
 * An incident is a record of something that went wrong in your cloud infrastructure.
 * 
 * This class captures all the essential information needed to:
 * - Identify what broke (serviceName, errorType)
 * - Understand the impact (severity, description)
 * - Track the resolution process (status, timestamps)
 * - Enable distributed tracing (correlationId)
 * 
 * Example incident: Payment API returning 500 errors due to database connection timeout
 */
public class Incident {
    
    /** Unique identifier for this incident (auto-generated) */
    private String id;
    
    /** Short, descriptive title of what went wrong */
    private String title;
    
    /** Detailed description of the problem and its symptoms */
    private String description;
    
    /** How serious this incident is (CRITICAL, HIGH, MEDIUM, LOW) */
    private Severity severity;
    
    /** Current stage of incident resolution (OPEN, INVESTIGATING, MITIGATED, RESOLVED) */
    private IncidentStatus status;
    
    /** When this incident was first created (UTC timestamp) */
    private Instant timestamp;
    
    /** When this incident was last modified (UTC timestamp) */
    private Instant updatedAt;
    
    /** Which service or component failed (e.g., "payment-api", "user-auth") */
    private String serviceName;
    
    /** What type of failure occurred (NETWORK, APPLICATION, CONFIGURATION, RESOURCE) */
    private ErrorType errorType;
    
    /** ID that links related events across multiple services (for distributed tracing) */
    private String correlationId;

    /** Default constructor required by Spring Boot */
    public Incident() {}

    // Getter and setter methods for all fields
    // These allow Spring Boot to convert between JSON and Java objects
    
    /** Gets the unique incident ID */
    public String getId() { return id; }
    /** Sets the unique incident ID (usually auto-generated) */
    public void setId(String id) { this.id = id; }

    /** Gets the incident title */
    public String getTitle() { return title; }
    /** Sets the incident title (should be short and descriptive) */
    public void setTitle(String title) { this.title = title; }

    /** Gets the detailed description */
    public String getDescription() { return description; }
    /** Sets the detailed description (include symptoms and context) */
    public void setDescription(String description) { this.description = description; }

    /** Gets the severity level */
    public Severity getSeverity() { return severity; }
    /** Sets the severity level (determines response priority) */
    public void setSeverity(Severity severity) { this.severity = severity; }

    /** Gets the current status */
    public IncidentStatus getStatus() { return status; }
    /** Sets the current status (tracks resolution progress) */
    public void setStatus(IncidentStatus status) { this.status = status; }

    /** Gets when the incident was created */
    public Instant getTimestamp() { return this.timestamp; }
    /** Sets when the incident was created (usually Instant.now()) */
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }

    /** Gets when the incident was last updated */
    public Instant getUpdatedAt() { return updatedAt; }
    /** Sets when the incident was last updated (should be updated on every change) */
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }

    /** Gets which service failed */
    public String getServiceName() { return serviceName; }
    /** Sets which service failed (helps identify responsible team) */
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }

    /** Gets the type of error that occurred */
    public ErrorType getErrorType() { return errorType; }
    /** Sets the type of error (guides troubleshooting approach) */
    public void setErrorType(ErrorType errorType) { this.errorType = errorType; }

    /** Gets the correlation ID for distributed tracing */
    public String getCorrelationId() { return correlationId; }
    /** Sets the correlation ID (links related failures across services) */
    public void setCorrelationId(String correlationId){
        this.correlationId = correlationId;
    }
}