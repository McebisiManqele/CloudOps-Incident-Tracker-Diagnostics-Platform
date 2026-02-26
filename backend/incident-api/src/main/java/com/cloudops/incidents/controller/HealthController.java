package com.cloudops.incidents.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Health check endpoint for monitoring system availability.
 * 
 * This simple endpoint is critical for:
 * - Load balancers checking if the service is alive
 * - Monitoring systems verifying service health
 * - Kubernetes/Docker health probes
 * - Quick manual checks during deployments
 * 
 * URL: /health
 * 
 * If this endpoint returns 200 OK, the service is running.
 * If it doesn't respond, the service is down or unreachable.
 */
@RestController
@RequestMapping("/health")
public class HealthController {

    /**
     * GET /health
     * 
     * Simple health check that returns "OK" if the service is running.
     * 
     * This endpoint:
     * - Requires no authentication
     * - Returns immediately (no database calls)
     * - Always returns 200 OK if service is alive
     * 
     * Use cases:
     * - Load balancer: "Is this instance healthy?"
     * - Monitoring: "Is the API responding?"
     * - Deployment: "Did the new version start successfully?"
     * 
     * Example request:
     * GET http://localhost:8080/health
     * 
     * Example response:
     * HTTP 200 OK
     * "OK"
     * 
     * @return HTTP 200 OK with "OK" message
     */
    @GetMapping
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("OK");
    }
}