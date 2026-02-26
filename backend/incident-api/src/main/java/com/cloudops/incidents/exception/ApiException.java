package com.cloudops.incidents.exception;

/**
 * Custom exception for API-specific errors.
 * 
 * This exception allows us to control:
 * - The HTTP status code returned to clients
 * - The error message shown to users
 * 
 * Instead of generic 500 errors, we can return specific codes:
 * - 404 Not Found - Incident doesn't exist
 * - 400 Bad Request - Invalid input data
 * - 409 Conflict - Duplicate incident
 * 
 * Example usage:
 * throw new ApiException("Incident not found", 404);
 * 
 * This gets caught by GlobalExceptionHandler and converted to:
 * HTTP 404 Not Found
 * "Incident not found"
 */
public class ApiException extends RuntimeException {
    
    /** The HTTP status code to return (e.g., 404, 400, 500) */
    private final int statusCode;

    /**
     * Creates a new API exception with a message and status code.
     * 
     * @param message User-friendly error message
     * @param statusCode HTTP status code (404, 400, 500, etc.)
     */
    public ApiException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    /**
     * Gets the HTTP status code for this error.
     * 
     * @return HTTP status code
     */
    public int getStatusCode() {
        return statusCode;
    }
}