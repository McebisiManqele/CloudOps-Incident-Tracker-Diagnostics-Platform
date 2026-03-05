package com.cloudops.incidents.util;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for handling timestamps in CloudOps.
 * 
 * Note: This class uses Instant instead of LocalDateTime for cloud operations.
 * 
 * Why Instant is better for cloud systems:
 * - Always UTC (no timezone confusion)
 * - Works across global infrastructure
 * - Consistent timestamps regardless of server location
 * - ISO-8601 format (2024-01-15T14:30:00Z)
 * 
 * The 'Z' at the end means "Zulu time" (UTC).
 * 
 * This utility helps format timestamps for:
 * - API responses
 * - Log messages
 * - Database storage
 */
public class TimeUtil {
    
    /**
     * Formatter for ISO-8601 timestamps.
     * Format: 2024-01-15T14:30:00Z
     */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_INSTANT;

    /**
     * Converts an Instant to a formatted string.
     * 
     * Example:
     * Input: Instant.now()
     * Output: "2024-01-15T14:30:00Z"
     * 
     * @param instant The timestamp to format
     * @return ISO-8601 formatted string
     */
    public static String format(Instant instant) {
        return FORMATTER.format(instant);
    }

    /**
     * Parses a timestamp string back to an Instant.
     * 
     * Example:
     * Input: "2024-01-15T14:30:00Z"
     * Output: Instant object
     * 
     * @param dateTimeString The ISO-8601 formatted string
     * @return Instant object
     */
    public static Instant parse(String dateTimeString) {
        return Instant.parse(dateTimeString);
    }
}