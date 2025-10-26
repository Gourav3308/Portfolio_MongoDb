package com.portfolio.controller;

import com.portfolio.model.ContactMessage;
import com.portfolio.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*")
public class ContactController {

    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<Map<String, String>> sendContactMessage(@RequestBody ContactMessage contactMessage) {
        try {
            logger.info("Received contact form submission from: {}", contactMessage.getEmail());
            
            // Validate required fields
            if (contactMessage.getName() == null || contactMessage.getName().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(createErrorResponse("Name is required"));
            }
            if (contactMessage.getEmail() == null || contactMessage.getEmail().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(createErrorResponse("Email is required"));
            }
            if (contactMessage.getSubject() == null || contactMessage.getSubject().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(createErrorResponse("Subject is required"));
            }
            if (contactMessage.getMessage() == null || contactMessage.getMessage().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(createErrorResponse("Message is required"));
            }
            
            emailService.sendContactMessage(contactMessage);
            
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Message sent successfully!");
            
            logger.info("Contact message sent successfully for: {}", contactMessage.getEmail());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error sending contact message from: {}", contactMessage.getEmail(), e);
            
            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Failed to send message. Please try again or contact me directly at gouravkrsah78@gmail.com");
            
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping("/test")
    public ResponseEntity<Map<String, String>> testEmailService() {
        try {
            logger.info("Testing email service...");
            
            ContactMessage testMessage = new ContactMessage();
            testMessage.setName("Test User");
            testMessage.setEmail("test@example.com");
            testMessage.setSubject("Test Message");
            testMessage.setMessage("This is a test message to verify email functionality.");
            
            emailService.sendContactMessage(testMessage);
            
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Test email sent successfully!");
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error testing email service", e);
            
            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Email service test failed: " + e.getMessage());
            
            return ResponseEntity.status(500).body(response);
        }
    }

    private Map<String, String> createErrorResponse(String message) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", message);
        return response;
    }
}
