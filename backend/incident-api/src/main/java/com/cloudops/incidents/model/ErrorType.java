package com.cloudops.incidents.model;

/**
 * Represents the different types of errors that can cause incidents in cloud operations.
 * This helps engineers quickly identify where to look when troubleshooting problems.
 * 
 * Each error type points to a specific area of the system:
 * - NETWORK: Problems with connections, timeouts, DNS issues
 * - APPLICATION: Code bugs, logic errors, null pointer exceptions
 * - CONFIGURATION: Wrong settings, missing environment variables, bad configs
 * - RESOURCE: Running out of memory, CPU limits, disk space issues
 */
public enum ErrorType {
    
    /** 
     * Network-related failures like timeouts, connection drops, DNS problems.
     * Look at: Load balancers, network connectivity, DNS settings, firewalls.
     */
    NETWORK,
    
    /** 
     * Application code failures like bugs, exceptions, logic errors.
     * Look at: Application logs, code changes, exception stack traces.
     */
    APPLICATION,
    
    /** 
     * Configuration problems like wrong settings or missing variables.
     * Look at: Environment variables, config files, deployment settings.
     */
    CONFIGURATION,
    
    /** 
     * Resource exhaustion like out of memory, CPU limits, disk space.
     * Look at: System metrics, resource usage, scaling settings.
     */
    RESOURCE
}
