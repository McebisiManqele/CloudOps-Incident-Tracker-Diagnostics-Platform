package com.cloudops.incidents.service;

import com.cloudops.incidents.dto.IncidentRequest;
import com.cloudops.incidents.dto.IncidentResponse;
import com.cloudops.incidents.model.Incident;
import com.cloudops.incidents.model.IncidentStatus;
import com.cloudops.incidents.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service class that handles all business logic for incident management.
 * This is the brain of the incident tracking system - it coordinates between
 * the API layer (controllers) and the data layer (repositories).
 * 
 * Main responsibilities:
 * - Create new incidents from user requests
 * - Update existing incidents with new information
 * - Retrieve incident data for API responses
 * - Convert between different data formats (DTOs and entities)
 * - Apply business rules (like setting initial status to OPEN)
 * 
 * This class follows the Service Layer pattern, which keeps business logic
 * separate from web controllers and database access.
 */
@Service
public class IncidentService {

    /**
     * Repository for accessing incident data in the database.
     * Spring automatically injects this dependency.
     */
    @Autowired
    private IncidentRepository incidentRepository;

    /**
     * Gets all incidents from the database and returns them as API responses.
     * 
     * This method:
     * 1. Asks the repository for all incidents
     * 2. Converts each incident to an API response format
     * 3. Returns the list of responses
     * 
     * @return List of all incidents formatted for API responses
     */
    public List<IncidentResponse> getAllIncidents() {
        return incidentRepository.findAll().stream()
                .map(this::toResponse)  // Convert each incident to response format
                .collect(Collectors.toList());
    }

    /**
     * Gets a specific incident by its ID and returns it as an API response.
     * 
     * @param id The unique identifier of the incident to retrieve
     * @return The incident formatted for API response
     */
    public IncidentResponse getIncident(String id) {
        Incident incident = incidentRepository.findById(id);
        return toResponse(incident);
    }

    /**
     * Creates a brand new incident from user input.
     * 
     * This method handles the complete incident creation process:
     * 1. Creates a new empty incident object
     * 2. Generates a unique ID
     * 3. Copies user-provided data (title, description, etc.)
     * 4. Sets system-controlled fields (status=OPEN, timestamps)
     * 5. Saves to database
     * 6. Returns the created incident as API response
     * 
     * Business rules applied:
     * - All new incidents start with status OPEN
     * - Creation and update timestamps are set to current time
     * - System generates unique ID (user cannot specify)
     * 
     * @param request User input containing incident details
     * @return The newly created incident formatted for API response
     */
    public IncidentResponse createIncident(IncidentRequest request) {
        // Create new incident object
        Incident incident = new Incident();
        
        // Set system-controlled fields
        incident.setId(UUID.randomUUID().toString());  // Generate unique ID
        incident.setStatus(IncidentStatus.OPEN);       // All incidents start as OPEN
        incident.setTimestamp(Instant.now());          // When created
        incident.setUpdatedAt(Instant.now());          // Same as creation time initially
        
        // Copy user-provided data
        incident.setTitle(request.getTitle());
        incident.setDescription(request.getDescription());
        incident.setSeverity(request.getSeverity());
        incident.setServiceName(request.getServiceName());
        incident.setErrorType(request.getErrorType());
        incident.setCorrelationId(request.getCorrelationId());
        
        // Save to database and return response
        incident = incidentRepository.save(incident);
        return toResponse(incident);
    }

    /**
     * Updates an existing incident with new information.
     * 
     * This method:
     * 1. Finds the existing incident by ID
     * 2. Updates it with new data from the request
     * 3. Updates the "last modified" timestamp
     * 4. Saves changes to database
     * 5. Returns updated incident as API response
     * 
     * Note: This method does NOT change:
     * - The incident ID (never changes)
     * - The creation timestamp (historical record)
     * - The status (would need separate endpoint for status changes)
     * 
     * @param id The ID of the incident to update
     * @param request New data to apply to the incident
     * @return The updated incident formatted for API response
     */
    public IncidentResponse updateIncident(String id, IncidentRequest request) {
        // Find existing incident
        Incident incident = incidentRepository.findById(id);
        
        // Update with new data
        incident.setTitle(request.getTitle());
        incident.setDescription(request.getDescription());
        incident.setSeverity(request.getSeverity());
        incident.setServiceName(request.getServiceName());
        incident.setErrorType(request.getErrorType());
        incident.setCorrelationId(request.getCorrelationId());
        
        // Update the "last modified" timestamp
        incident.setUpdatedAt(Instant.now());
        
        // Save changes and return response
        incident = incidentRepository.save(incident);
        return toResponse(incident);
    }

    /**
     * Deletes an incident from the system.
     * 
     * @param id The ID of the incident to delete
     */
    public void deleteIncident(String id) {
        incidentRepository.deleteById(id);
    }

    /**
     * Converts an internal Incident object to an API response format.
     * 
     * This is a crucial method that acts as a translator between:
     * - Internal data format (Incident entity)
     * - External API format (IncidentResponse DTO)
     * 
     * Why we need this conversion:
     * - API responses should be stable (don't change when internal structure changes)
     * - Security (don't expose internal database fields)
     * - Flexibility (can format data differently for API consumers)
     * 
     * This method copies every field from the internal incident to the response object.
     * 
     * @param incident The internal incident object from database
     * @return API-safe response object with the same data
     */
    private IncidentResponse toResponse(Incident incident) {
        IncidentResponse response = new IncidentResponse();
        
        // Copy all fields from incident to response
        response.setId(incident.getId());
        response.setTitle(incident.getTitle());
        response.setDescription(incident.getDescription());
        response.setSeverity(incident.getSeverity());
        response.setStatus(incident.getStatus());
        response.setCreatedAt(incident.getTimestamp());      // Note: timestamp becomes createdAt
        response.setUpdatedAt(incident.getUpdatedAt());
        response.setServiceName(incident.getServiceName());
        response.setErrorType(incident.getErrorType());
        response.setCorrelationId(incident.getCorrelationId());
        
        return response;
    }
}