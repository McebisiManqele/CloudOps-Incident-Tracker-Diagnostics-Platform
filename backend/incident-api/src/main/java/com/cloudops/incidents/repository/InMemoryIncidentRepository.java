package com.cloudops.incidents.repository;

import com.cloudops.incidents.exception.ApiException;
import com.cloudops.incidents.model.Incident;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In-memory implementation of IncidentRepository.
 * Stores incidents in RAM using a HashMap.
 * 
 * Perfect for:
 * - Local development and testing
 * - Quick prototyping
 * - Learning how repositories work
 * 
 * Limitations:
 * - Data is lost when application restarts
 * - Not suitable for production
 * - No persistence across deployments
 * 
 * How it works:
 * - Uses ConcurrentHashMap for thread-safe storage
 * - Incident ID is the key, Incident object is the value
 * - All operations happen in memory (very fast)
 * 
 * To use a real database instead, create a different implementation
 * (e.g., SqliteIncidentRepository or DynamoDbIncidentRepository)
 * and Spring will automatically use it.
 */
@Repository
public class InMemoryIncidentRepository implements IncidentRepository {

    /**
     * In-memory storage using a thread-safe HashMap.
     * Key: Incident ID (String)
     * Value: Incident object
     * 
     * ConcurrentHashMap is used because multiple API requests
     * might try to access incidents at the same time.
     */
    private final Map<String, Incident> storage = new ConcurrentHashMap<>();

    /**
     * Gets all incidents from memory.
     * 
     * Implementation:
     * - Extracts all values from the HashMap
     * - Converts to ArrayList for easy handling
     * 
     * @return List of all incidents (empty if none exist)
     */
    @Override
    public List<Incident> findAll() {
        return new ArrayList<>(storage.values());
    }

    /**
     * Finds a specific incident by ID.
     * 
     * Implementation:
     * - Looks up incident in HashMap by ID
     * - Throws 404 error if not found
     * 
     * @param id The incident ID to search for
     * @return The incident if found
     * @throws ApiException with 404 status if incident doesn't exist
     */
    @Override
    public Incident findById(String id) {
        Incident incident = storage.get(id);
        if (incident == null) {
            throw new ApiException("Incident not found with id: " + id, 404);
        }
        return incident;
    }

    /**
     * Saves an incident to memory.
     * 
     * Implementation:
     * - Stores incident in HashMap using its ID as key
     * - If ID already exists, overwrites (update)
     * - If ID is new, creates new entry (create)
     * 
     * @param incident The incident to save
     * @return The saved incident (same object)
     */
    @Override
    public Incident save(Incident incident) {
        storage.put(incident.getId(), incident);
        return incident;
    }

    /**
     * Deletes an incident from memory.
     * 
     * Implementation:
     * - Checks if incident exists first
     * - Throws 404 error if not found
     * - Removes from HashMap if found
     * 
     * @param id The ID of the incident to delete
     * @throws ApiException with 404 status if incident doesn't exist
     */
    @Override
    public void deleteById(String id) {
        if (!storage.containsKey(id)) {
            throw new ApiException("Incident not found with id: " + id, 404);
        }
        storage.remove(id);
    }
}
