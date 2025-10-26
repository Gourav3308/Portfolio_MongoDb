package com.portfolio.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;

@Document(collection = "certifications")
public class Certification {
    
    @Id
    private String id;
    
    @NotBlank(message = "Certification name is required")
    private String name;
    
    @NotBlank(message = "Issuing organization is required")
    private String issuingOrganization;
    
    private String issueDate;
    private String expiryDate;
    private String credentialId;
    private String credentialUrl;
    private String description;
    private String status; // Active, Expired, Ongoing
    
    // Constructors
    public Certification() {}
    
    public Certification(String name, String issuingOrganization, String issueDate, String expiryDate, 
                        String credentialId, String credentialUrl, String description, String status) {
        this.name = name;
        this.issuingOrganization = issuingOrganization;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
        this.credentialId = credentialId;
        this.credentialUrl = credentialUrl;
        this.description = description;
        this.status = status;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getIssuingOrganization() {
        return issuingOrganization;
    }
    
    public void setIssuingOrganization(String issuingOrganization) {
        this.issuingOrganization = issuingOrganization;
    }
    
    public String getIssueDate() {
        return issueDate;
    }
    
    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }
    
    public String getExpiryDate() {
        return expiryDate;
    }
    
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    public String getCredentialId() {
        return credentialId;
    }
    
    public void setCredentialId(String credentialId) {
        this.credentialId = credentialId;
    }
    
    public String getCredentialUrl() {
        return credentialUrl;
    }
    
    public void setCredentialUrl(String credentialUrl) {
        this.credentialUrl = credentialUrl;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}



