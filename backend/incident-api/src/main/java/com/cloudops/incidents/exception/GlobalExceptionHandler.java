package com.cloudops.incidents.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler that catches all errors in the application.
 * This prevents ugly stack traces from reaching API clients.
 * 
 * Instead of:
 * java.lang.NullPointerException at line 42...
 * 
 * Clients get:
 * HTTP 500 Internal Server Error
 * "Internal server error"
 * 
 * Benefits:
 * - User-friendly error messages
 * - Consistent error format across all endpoints
 * - Security (doesn't expose internal implementation details)
 * - Proper HTTP status codes
 * 
 * This class uses Spring's @ControllerAdvice to automatically catch
 * exceptions from all controllers.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles custom ApiException errors.
     * 
     * When code throws: new ApiException("Incident not found", 404)
     * Client receives: HTTP 404 with message "Incident not found"
     * 
     * This allows precise control over error responses.
     * 
     * Example scenarios:
     * - Incident not found → 404 Not Found
     * - Invalid input → 400 Bad Request
     * - Duplicate incident → 409 Conflict
     * 
     * @param e The ApiException that was thrown
     * @return HTTP response with appropriate status code and message
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<String> handleApiException(ApiException e) {
        return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
    }

    /**
     * Handles all other unexpected exceptions.
     * 
     * This is the safety net that catches:
     * - NullPointerException
     * - Database connection errors
     * - Any other runtime errors
     * 
     * Instead of crashing or showing stack traces, returns:
     * HTTP 500 Internal Server Error
     * "Internal server error"
     * 
     * In production, you would:
     * - Log the full error for debugging
     * - Alert the operations team
     * - Return generic message to client (security)
     * 
     * @param e The unexpected exception
     * @return HTTP 500 with generic error message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        return ResponseEntity.status(500).body("Internal server error");
    }
}