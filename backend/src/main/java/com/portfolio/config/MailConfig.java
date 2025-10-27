package com.portfolio.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

    @Bean
    @ConditionalOnProperty(name = "spring.mail.username", havingValue = "", matchIfMissing = true)
    public JavaMailSender dummyMailSender() {
        // Return a dummy mail sender when email is not configured
        return new JavaMailSenderImpl();
    }
}
