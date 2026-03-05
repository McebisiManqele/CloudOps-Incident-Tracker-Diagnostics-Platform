package com.cloudops.incidents.dto;

import com.cloudops.incidents.model.ErrorType;
import com.cloudops.incidents.model.Severity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for creating or updating incidents via the API.
 * This represents the information that users send when reporting a new incident.
 * 
 * This class only contains the fields that users can provide:
 * - What happened (title, description)
 * - How bad it is (severity)
 * - Where it happened (serviceName)
 * - What type of problem (errorType)
 * - How to trace it (correlationId)
 * 
 * Fields like ID, timestamps, and status are automatically set by the system.
 * 
 * Validation rules:
 * - title: Required, 1-200 characters
 * - description: Optional, max 1000 characters
 * - severity: Required
 * - serviceName: Required, 1-100 characters
 * - errorType: Required
 * - correlationId: Optional, max 100 characters
 * 
 * Example JSON request:
 * {
 *   "title": "Payment API Down",
 *   "description": "All payment requests returning 500 errors",
 *   "severity": "CRITICAL",
 *   "serviceName": "payment-processor",
 *   "errorType": "NETWORK",
 *   "correlationId": "req-abc-123"
 * }
 */
public class IncidentRequest {
    
    /** 
     * Short, descriptive title of the incident.
     * Required field, must be between 1 and 200 characters.
     */
    @NotBlank(message = "Title is required")
    @Size(min = 1, max = 200, message = "Title must be between 1 and 200 characters")
    private String title;
    
    /** 
     * Detailed description of what went wrong.
     * Optional field, maximum 1000 characters.
     */
    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;
    
    /** 
     * How serious this incident is.
     * Required field, must be one of: LOW, MEDIUM, HIGH, CRITICAL
     */
    @NotNull(message = "Severity is required")
    private Severity severity;
    
    /** 
     * Which service or component failed.
     * Required field, must be between 1 and 100 characters.
     */
    @NotBlank(message = "Service name is required")
    @Size(min = 1, max = 100, message = "Service name must be between 1 and 100 characters")
    private String serviceName;
    
    /** 
     * What type of failure occurred.
     * Required field, must be one of: NETWORK, APPLICATION, CONFIGURATION, RESOURCE
     */
    @NotNull(message = "Error type is required")
    private ErrorType errorType;
    
    /** 
     * Optional ID for linking related events across services.
     * Maximum 100 characters.
     */
    @Size(max = 100, message = "Correlation ID must not exceed 100 characters")
    private String correlationId;

    /** Default constructor required by Spring Boot for JSON deserialization */
    public IncidentRequest() {}

    // Getter and setter methods for JSON conversion
    
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