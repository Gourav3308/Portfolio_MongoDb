package com.portfolio.controller;

import com.portfolio.model.ContactMessage;
import com.portfolio.repository.ContactMessageRepository;
import com.portfolio.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*")
public class ContactController {

    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private EmailService emailService;

    @Autowired
    private ContactMessageRepository contactMessageRepository;

    @Value("${spring.mail.host:NOT_SET}")
    private String mailHost;

    @Value("${spring.mail.port:NOT_SET}")
    private String mailPort;

    @Value("${spring.mail.username:NOT_SET}")
    private String mailUsername;

    @Value("${spring.profiles.active:NOT_SET}")
    private String activeProfile;

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
            response.put("message", "Message received successfully! I'll get back to you soon.");
            
            logger.info("Contact message processed successfully for: {}", contactMessage.getEmail());
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
            response.put("message", "Test message processed successfully!");
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error testing email service", e);
            
            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Email service test failed: " + e.getMessage());
            
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping("/messages")
    public ResponseEntity<List<ContactMessage>> getAllContactMessages() {
        try {
            List<ContactMessage> messages = contactMessageRepository.findAll();
            logger.info("Retrieved {} contact messages", messages.size());
            return ResponseEntity.ok(messages);
        } catch (Exception e) {
            logger.error("Error retrieving contact messages", e);
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/messages/count")
    public ResponseEntity<Map<String, Object>> getContactMessageCount() {
        try {
            long count = contactMessageRepository.count();
            Map<String, Object> response = new HashMap<>();
            response.put("count", count);
            response.put("status", "success");
            logger.info("Contact message count: {}", count);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error retrieving contact message count", e);
            Map<String, Object> response = new HashMap<>();
            response.put("count", 0);
            response.put("status", "error");
            response.put("message", "Failed to retrieve count");
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping("/debug/config")
    public ResponseEntity<Map<String, Object>> getDebugConfig() {
        Map<String, Object> response = new HashMap<>();
        response.put("activeProfile", activeProfile);
        response.put("mailHost", mailHost);
        response.put("mailPort", mailPort);
        response.put("mailUsername", mailUsername);
        response.put("status", "success");
        
        logger.info("Debug config - Profile: {}, Mail Host: {}, Mail Port: {}, Mail Username: {}", 
                   activeProfile, mailHost, mailPort, mailUsername);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/test-email")
    public ResponseEntity<Map<String, String>> testEmailConnection() {
        try {
            logger.info("Testing email connection...");
            
            ContactMessage testMessage = new ContactMessage();
            testMessage.setName("Email Test");
            testMessage.setEmail("gouravkrsah78@gmail.com");
            testMessage.setSubject("Email Configuration Test");
            testMessage.setMessage("This is a test to verify email configuration is working properly.");
            
            emailService.sendContactMessage(testMessage);
            
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Email test completed! Check logs for details.");
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Email test failed", e);
            
            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Email test failed: " + e.getMessage());
            
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
