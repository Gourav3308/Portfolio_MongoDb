package com.portfolio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class HomeController {

    @GetMapping
    public ResponseEntity<Map<String, Object>> home() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Welcome to Gourav Kumar's Portfolio API");
        response.put("status", "success");
        response.put("version", "1.0.0");
        
        Map<String, String> endpoints = new HashMap<>();
        endpoints.put("Personal Info", "/api/personal-info");
        endpoints.put("Projects", "/api/projects");
        endpoints.put("Experience", "/api/experience");
        endpoints.put("Education", "/api/education");
        endpoints.put("Skills", "/api/skills");
        endpoints.put("Certifications", "/api/certifications");
        endpoints.put("Contact", "/api/contact/send");
        endpoints.put("Health Check", "/api/health");
        
        response.put("available_endpoints", endpoints);
        response.put("documentation", "This is a REST API for Gourav Kumar's portfolio website");
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("message", "Portfolio API is running successfully");
        response.put("timestamp", java.time.Instant.now().toString());
        return ResponseEntity.ok(response);
    }
}
