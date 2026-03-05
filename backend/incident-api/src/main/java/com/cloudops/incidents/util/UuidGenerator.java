package com.cloudops.incidents.util;

import java.util.UUID;

/**
 * Utility class for generating unique identifiers.
 * 
 * Uses UUID (Universally Unique Identifier) which guarantees
 * uniqueness across all systems and time.
 * 
 * Example output: "550e8400-e29b-41d4-a716-446655440000"
 * 
 * Why UUIDs are perfect for incident IDs:
 * - Globally unique (no collisions even across multiple servers)
 * - No need for centralized ID generation
 * - Can be generated offline
 * - 128-bit number = virtually impossible to duplicate
 * 
 * Used by IncidentService when creating new incidents.
 */
public class UuidGenerator {
    
    /**
     * Generates a new random UUID as a string.
     * 
     * Format: 8-4-4-4-12 hexadecimal digits
     * Example: "550e8400-e29b-41d4-a716-446655440000"
     * 
     * @return A unique identifier string
     */
    public static String generate() {
        return UUID.randomUUID().toString();
    }
}