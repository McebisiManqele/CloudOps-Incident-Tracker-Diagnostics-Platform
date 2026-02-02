package com.cloudops.incidents.dto;

import com.cloudops.incidents.model.Severity;

public class IncidentRequest {
    private String title;
    private String description;
    private Severity severity;

    public IncidentRequest() {}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Severity getSeverity() { return severity; }
    public void setSeverity(Severity severity) { this.severity = severity; }
}