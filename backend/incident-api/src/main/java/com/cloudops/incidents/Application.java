package com.cloudops.incidents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the CloudOps Incident Tracker API.
 * 
 * This class starts the entire Spring Boot application.
 * When you run this, it:
 * 1. Scans for all components (controllers, services, repositories)
 * 2. Sets up the web server (default port 8080)
 * 3. Configures dependency injection
 * 4. Initializes database connections
 * 5. Starts listening for HTTP requests
 * 
 * The @SpringBootApplication annotation does three things:
 * - @Configuration: Allows defining beans
 * - @EnableAutoConfiguration: Automatically configures Spring based on dependencies
 * - @ComponentScan: Finds all components in this package and sub-packages
 * 
 * To run the application:
 * - From IDE: Run this main method
 * - From command line: mvn spring-boot:run
 * - As JAR: java -jar incident-tracker.jar
 * 
 * Once running, the API is available at:
 * http://localhost:8080
 * 
 * Health check:
 * http://localhost:8080/health
 * 
 * API endpoints:
 * http://localhost:8080/api/incidents
 */
@SpringBootApplication
public class Application {
    
    /**
     * Main method that starts the Spring Boot application.
     * 
     * @param args Command line arguments (can be used for configuration)
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}