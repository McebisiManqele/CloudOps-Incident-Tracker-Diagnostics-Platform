package com.cloudops.incidents.controller;

import com.cloudops.incidents.dto.IncidentRequest;
import com.cloudops.incidents.dto.IncidentResponse;
import com.cloudops.incidents.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    @Autowired
    private IncidentService incidentService;

    @GetMapping
    public ResponseEntity<List<IncidentResponse>> getAllIncidents() {
        return ResponseEntity.ok(incidentService.getAllIncidents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncidentResponse> getIncident(@PathVariable String id) {
        return ResponseEntity.ok(incidentService.getIncident(id));
    }

    @PostMapping
    public ResponseEntity<IncidentResponse> createIncident(@RequestBody IncidentRequest request) {
        return ResponseEntity.ok(incidentService.createIncident(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IncidentResponse> updateIncident(@PathVariable String id, @RequestBody IncidentRequest request) {
        return ResponseEntity.ok(incidentService.updateIncident(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncident(@PathVariable String id) {
        incidentService.deleteIncident(id);
        return ResponseEntity.noContent().build();
    }
}