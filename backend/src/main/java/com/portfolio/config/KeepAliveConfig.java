package com.portfolio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
@EnableScheduling
public class KeepAliveConfig {
    
    private static final Logger logger = LoggerFactory.getLogger(KeepAliveConfig.class);
    
    @Value("${server.port:8080}")
    private String serverPort;
    
    private final RestTemplate restTemplate = new RestTemplate();
    
    // Ping the health endpoint every 4 minutes to keep the service alive
    @Scheduled(fixedRate = 240000) // 4 minutes
    public void keepAlive() {
        try {
            String healthUrl = "http://localhost:" + serverPort + "/api/health";
            restTemplate.getForObject(healthUrl, String.class);
            logger.info("Keep-alive ping successful");
        } catch (Exception e) {
            logger.warn("Keep-alive ping failed: {}", e.getMessage());
        }
    }
    
    // Additional health check every 10 minutes
    @Scheduled(fixedRate = 600000) // 10 minutes
    public void healthCheck() {
        try {
            String healthUrl = "http://localhost:" + serverPort + "/api/health";
            String response = restTemplate.getForObject(healthUrl, String.class);
            logger.info("Health check successful: {}", response);
        } catch (Exception e) {
            logger.error("Health check failed: {}", e.getMessage());
        }
    }
}
