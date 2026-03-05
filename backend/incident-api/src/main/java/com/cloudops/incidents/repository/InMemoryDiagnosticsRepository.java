package com.cloudops.incidents.repository;

import com.cloudops.incidents.model.DiagnosticRecord;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * In-memory implementation of DiagnosticsRepository.
 * Stores diagnostic records in RAM using a HashMap.
 * 
 * Diagnostic records are linked to incidents via incidentId.
 * Multiple diagnostic records can belong to one incident.
 */
@Repository
public class InMemoryDiagnosticsRepository implements DiagnosticsRepository {

    /**
     * In-memory storage for diagnostic records.
     * Key: Diagnostic record ID
     * Value: DiagnosticRecord object
     */
    private final Map<String, DiagnosticRecord> storage = new ConcurrentHashMap<>();

    /**
     * Finds all diagnostic records for a specific incident.
     * 
     * @param incidentId The incident ID to search for
     * @return List of diagnostic records (empty if none found)
     */
    @Override
    public List<DiagnosticRecord> findByIncidentId(String incidentId) {
        return storage.values().stream()
                .filter(record -> incidentId.equals(record.getIncidentId()))
                .collect(Collectors.toList());
    }

    /**
     * Saves a diagnostic record to memory.
     * 
     * @param record The diagnostic record to save
     * @return The saved record
     */
    @Override
    public DiagnosticRecord save(DiagnosticRecord record) {
        storage.put(record.getId(), record);
        return record;
    }
}
