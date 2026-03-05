package com.cloudops.incidents.repository;

import com.cloudops.incidents.model.Incident;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for incident data access operations.
 * This defines what operations we can perform on incident data.
 * 
 * The actual implementation can be:
 * - In-memory storage (for testing)
 * - SQLite database (for local development)
 * - DynamoDB (for AWS production)
 * 
 * By using an interface, we can swap implementations without
 * changing the service layer code.
 * 
 * Operations:
 * - findAll(): Get all incidents
 * - findById(): Get specific incident
 * - save(): Create or update incident
 * - deleteById(): Remove incident
 */
@Repository
public interface IncidentRepository {
    
    /**
     * Retrieves all incidents from storage.
     * 
     * @return List of all incidents (empty list if none exist)
     */
    List<Incident> findAll();
    
    /**
     * Finds a specific incident by its unique ID.
     * 
     * @param id The incident ID to search for
     * @return The incident if found, null otherwise
     */
    Incident findById(String id);
    
    /**
     * Saves an incident to storage.
     * If incident ID exists, updates it.
     * If incident ID is new, creates it.
     * 
     * @param incident The incident to save
     * @return The saved incident
     */
    Incident save(Incident incident);
    
    /**
     * Deletes an incident from storage.
     * 
     * @param id The ID of the incident to delete
     */
    void deleteById(String id);
}