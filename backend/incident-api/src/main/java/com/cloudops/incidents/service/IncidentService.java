package com.cloudops.incidents.service;

import com.cloudops.incidents.dto.IncidentRequest;
import com.cloudops.incidents.dto.IncidentResponse;
import com.cloudops.incidents.model.Incident;
import com.cloudops.incidents.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class IncidentService {

    @Autowired
    private IncidentRepository incidentRepository;

    public List<IncidentResponse> getAllIncidents() {
        return incidentRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public IncidentResponse getIncident(String id) {
        Incident incident = incidentRepository.findById(id);
        return toResponse(incident);
    }

    public IncidentResponse createIncident(IncidentRequest request) {
        Incident incident = new Incident();
        incident.setId(UUID.randomUUID().toString());
        incident.setTitle(request.getTitle());
        incident.setDescription(request.getDescription());
        incident.setSeverity(request.getSeverity());
        incident.setStatus("OPEN");
        incident.setCreatedAt(LocalDateTime.now());
        incident.setUpdatedAt(LocalDateTime.now());
        
        incident = incidentRepository.save(incident);
        return toResponse(incident);
    }

    public IncidentResponse updateIncident(String id, IncidentRequest request) {
        Incident incident = incidentRepository.findById(id);
        incident.setTitle(request.getTitle());
        incident.setDescription(request.getDescription());
        incident.setSeverity(request.getSeverity());
        incident.setUpdatedAt(LocalDateTime.now());
        
        incident = incidentRepository.save(incident);
        return toResponse(incident);
    }

    public void deleteIncident(String id) {
        incidentRepository.deleteById(id);
    }

    private IncidentResponse toResponse(Incident incident) {
        IncidentResponse response = new IncidentResponse();
        response.setId(incident.getId());
        response.setTitle(incident.getTitle());
        response.setDescription(incident.getDescription());
        response.setSeverity(incident.getSeverity());
        response.setStatus(incident.getStatus());
        response.setCreatedAt(incident.getCreatedAt());
        response.setUpdatedAt(incident.getUpdatedAt());
        return response;
    }
}