package com.cloudops.incidents.repository;

import com.cloudops.incidents.model.Incident;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidentRepository {
    List<Incident> findAll();
    Incident findById(String id);
    Incident save(Incident incident);
    void deleteById(String id);
}