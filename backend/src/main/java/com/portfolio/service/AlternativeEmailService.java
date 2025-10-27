package com.portfolio.service;

import com.portfolio.model.ContactMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AlternativeEmailService {

    private static final Logger logger = LoggerFactory.getLogger(AlternativeEmailService.class);

    @Value("${app.email.sendgrid.api-key:}")
    private String sendGridApiKey;

    @Value("${app.email.sendgrid.from:gouravkrsah78@gmail.com}")
    private String fromEmail;

    @Value("${app.contact.email:gouravkrsah78@gmail.com}")
    private String contactEmail;

    private final RestTemplate restTemplate = new RestTemplate();

    public boolean sendEmailViaSendGrid(ContactMessage contactMessage) {
        if (sendGridApiKey.isEmpty()) {
            logger.warn("SendGrid API key not configured - skipping email");
            return false;
        }

        try {
            // Send admin notification
            boolean adminSent = sendAdminNotification(contactMessage);
            
            // Send confirmation email
            boolean confirmationSent = sendConfirmationEmail(contactMessage);
            
            return adminSent && confirmationSent;
        } catch (Exception e) {
            logger.error("Failed to send email via SendGrid", e);
            return false;
        }
    }

    private boolean sendAdminNotification(ContactMessage contactMessage) {
        try {
            String url = "https://api.sendgrid.com/v3/mail/send";
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(sendGridApiKey);

            Map<String, Object> emailData = new HashMap<>();
            emailData.put("from", Map.of("email", fromEmail, "name", "Portfolio Contact Form"));
            emailData.put("to", new Object[]{Map.of("email", contactEmail, "name", "Gourav Kumar")});
            emailData.put("subject", "New Contact Message: " + contactMessage.getSubject());
            
            String emailBody = String.format(
                "You have received a new contact message:\n\n" +
                "Name: %s\n" +
                "Email: %s\n" +
                "Subject: %s\n\n" +
                "Message:\n%s\n\n" +
                "Sent at: %s",
                contactMessage.getName(),
                contactMessage.getEmail(),
                contactMessage.getSubject(),
                contactMessage.getMessage(),
                contactMessage.getCreatedAt()
            );
            
            emailData.put("text", emailBody);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(emailData, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            
            logger.info("Admin notification sent via SendGrid: {}", response.getStatusCode());
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            logger.error("Failed to send admin notification via SendGrid", e);
            return false;
        }
    }

    private boolean sendConfirmationEmail(ContactMessage contactMessage) {
        try {
            String url = "https://api.sendgrid.com/v3/mail/send";
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(sendGridApiKey);

            Map<String, Object> emailData = new HashMap<>();
            emailData.put("from", Map.of("email", fromEmail, "name", "Gourav Kumar"));
            emailData.put("to", new Object[]{Map.of("email", contactMessage.getEmail(), "name", contactMessage.getName())});
            emailData.put("subject", "Thank you for contacting Gourav!");
            
            String confirmationBody = String.format(
                "Dear %s,\n\n" +
                "Thank you for reaching out! I have received your message regarding '%s' and will get back to you soon.\n\n" +
                "Your message:\n%s\n\n" +
                "I appreciate your interest and look forward to connecting with you.\n\n" +
                "Best regards,\n" +
                "Gourav\n" +
                "Full Stack Developer\n\n" +
                "---\n" +
                "Contact Information:\n" +
                "Email: gouravkrsah78@gmail.com\n" +
                "Phone: +91 7903840357\n" +
                "GitHub: https://github.com/Gourav3308\n" +
                "LinkedIn: https://www.linkedin.com/in/gourav-java-dev/",
                contactMessage.getName(),
                contactMessage.getSubject(),
                contactMessage.getMessage()
            );
            
            emailData.put("text", confirmationBody);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(emailData, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            
            logger.info("Confirmation email sent via SendGrid: {}", response.getStatusCode());
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            logger.error("Failed to send confirmation email via SendGrid", e);
            return false;
        }
    }
}
