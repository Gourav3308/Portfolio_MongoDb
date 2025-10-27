package com.portfolio.service;

import com.portfolio.model.ContactMessage;
import com.portfolio.repository.ContactMessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ContactMessageRepository contactMessageRepository;

    @Value("${spring.mail.username:}")
    private String fromEmail;

    @Value("${app.contact.email}")
    private String contactEmail;

    public void sendContactMessage(ContactMessage contactMessage) {
        try {
            logger.info("Starting to process contact message from: {}", contactMessage.getEmail());
            
            // Save to database first
            ContactMessage savedMessage = contactMessageRepository.save(contactMessage);
            logger.info("Contact message saved to database with ID: {}", savedMessage.getId());

            // Try to send emails, but don't fail if they don't work
            boolean adminEmailSent = sendEmailToAdmin(savedMessage);
            boolean confirmationEmailSent = sendConfirmationEmail(savedMessage);
            
            if (adminEmailSent && confirmationEmailSent) {
                logger.info("Contact message processed successfully with emails for: {}", contactMessage.getEmail());
            } else {
                logger.warn("Contact message saved to database but email sending failed for: {}", contactMessage.getEmail());
            }
        } catch (Exception e) {
            logger.error("Error processing contact message from: {}", contactMessage.getEmail(), e);
            // Don't throw exception - just log the error and continue
            logger.warn("Contact message was saved to database but email processing failed");
        }
    }

    private boolean sendEmailToAdmin(ContactMessage contactMessage) {
        if (mailSender == null) {
            logger.warn("MailSender is not available - skipping admin notification email");
            return false;
        }
        
        try {
            logger.info("Sending admin notification email to: {}", contactEmail);
            
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(contactEmail);
            message.setSubject("New Contact Message: " + contactMessage.getSubject());
            
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
            
            message.setText(emailBody);
            mailSender.send(message);
            
            logger.info("Admin notification email sent successfully");
            return true;
        } catch (Exception e) {
            logger.error("Failed to send admin notification email", e);
            return false;
        }
    }

    private boolean sendConfirmationEmail(ContactMessage contactMessage) {
        if (mailSender == null) {
            logger.warn("MailSender is not available - skipping confirmation email");
            return false;
        }
        
        try {
            logger.info("Sending confirmation email to: {}", contactMessage.getEmail());
            
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(contactMessage.getEmail());
            message.setSubject("Thank you for contacting Gourav!");
            
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
            
            message.setText(confirmationBody);
            mailSender.send(message);
            
            logger.info("Confirmation email sent successfully to: {}", contactMessage.getEmail());
            return true;
        } catch (Exception e) {
            logger.error("Failed to send confirmation email to: {}", contactMessage.getEmail(), e);
            return false;
        }
    }
}


