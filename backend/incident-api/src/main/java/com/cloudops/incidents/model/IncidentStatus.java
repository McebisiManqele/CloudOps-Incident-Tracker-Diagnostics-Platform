package com.cloudops.incidents.model;

/**
 * Represents the lifecycle status of an incident in the CloudOps system.
 * Each status shows what stage the incident is in, from discovery to resolution.
 * 
 * Status flow: OPEN → INVESTIGATING → MITIGATED → RESOLVED
 * 
 * - OPEN: Just discovered, nobody assigned yet
 * - INVESTIGATING: Someone is actively looking at the problem
 * - MITIGATED: Temporary fix applied, service is working again
 * - RESOLVED: Root cause found and permanently fixed
 */
public enum IncidentStatus {
    
    /** 
     * Incident just created, waiting for someone to start investigating.
     * Action needed: Assign to on-call engineer or team.
     */
    OPEN,
    
    /** 
     * Engineer is actively working on finding the root cause.
     * Action needed: Continue investigation, update stakeholders.
     */
    INVESTIGATING,
    
    /** 
     * Temporary fix applied, service is working but not permanently fixed.
     * Action needed: Schedule permanent fix, monitor for recurrence.
     */
    MITIGATED,
    
    /** 
     * Root cause identified and permanently fixed, incident closed.
     * Action needed: Generate post-incident report, update documentation.
     */
    RESOLVED
}
