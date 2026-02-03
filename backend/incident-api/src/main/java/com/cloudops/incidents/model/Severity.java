package com.cloudops.incidents.model;

/**
 * Represents how serious an incident is in terms of business and user impact.
 * This determines response priority, escalation procedures, and who gets notified.
 * 
 * Priority order: CRITICAL > HIGH > MEDIUM > LOW
 * 
 * - CRITICAL: Service completely down, revenue impact, all hands on deck
 * - HIGH: Major functionality broken, significant user impact
 * - MEDIUM: Performance issues, some users affected
 * - LOW: Minor problems, monitoring alerts only
 */
public enum Severity {
    
    /** 
     * Minor issues that don't affect users directly.
     * Examples: Monitoring alerts, performance warnings, non-critical errors.
     * Response: Normal business hours, no escalation needed.
     */
    LOW,
    
    /** 
     * Performance degradation affecting some users.
     * Examples: Slow response times, intermittent errors, partial outages.
     * Response: Investigate within 2-4 hours, inform stakeholders.
     */
    MEDIUM,
    
    /** 
     * Major functionality broken with significant user impact.
     * Examples: Key features down, payment issues, login problems.
     * Response: Immediate investigation, escalate to senior engineers.
     */
    HIGH,
    
    /** 
     * Complete service outage with revenue impact.
     * Examples: Website down, API completely failing, data loss.
     * Response: All hands on deck, immediate escalation, executive notification.
     */
    CRITICAL
}