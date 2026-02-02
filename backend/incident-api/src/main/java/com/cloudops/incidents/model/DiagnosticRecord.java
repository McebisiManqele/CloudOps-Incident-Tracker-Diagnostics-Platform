package com.cloudops.incidents.model;

import java.time.LocalDateTime;

public class DiagnosticRecord {
    private String id;
    private String incidentId;
    private String source;
    private String data;
    private LocalDateTime timestamp;

    public DiagnosticRecord() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getIncidentId() { return incidentId; }
    public void setIncidentId(String incidentId) { this.incidentId = incidentId; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}