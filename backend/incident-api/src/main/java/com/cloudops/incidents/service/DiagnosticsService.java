package com.cloudops.incidents.service;

import com.cloudops.incidents.model.DiagnosticRecord;
import com.cloudops.incidents.repository.DiagnosticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosticsService {

    @Autowired
    private DiagnosticsRepository diagnosticsRepository;

    public List<DiagnosticRecord> getDiagnosticsByIncident(String incidentId) {
        return diagnosticsRepository.findByIncidentId(incidentId);
    }

    public DiagnosticRecord saveDiagnostic(DiagnosticRecord record) {
        return diagnosticsRepository.save(record);
    }
}