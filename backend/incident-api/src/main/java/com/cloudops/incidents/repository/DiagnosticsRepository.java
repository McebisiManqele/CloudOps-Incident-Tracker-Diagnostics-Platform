package com.cloudops.incidents.repository;

import com.cloudops.incidents.model.DiagnosticRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiagnosticsRepository {
    List<DiagnosticRecord> findByIncidentId(String incidentId);
    DiagnosticRecord save(DiagnosticRecord record);
}