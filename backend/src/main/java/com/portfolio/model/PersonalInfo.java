package com.portfolio.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Document(collection = "personal_info")
public class PersonalInfo {
    
    @Id
    private String id;
    
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;
    
    @NotBlank(message = "Title is required")
    @Size(max = 200, message = "Title must not exceed 200 characters")
    private String title;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    
    @NotBlank(message = "Phone is required")
    private String phone;
    
    @NotBlank(message = "Location is required")
    private String location;
    
    @NotBlank(message = "LinkedIn URL is required")
    private String linkedin;
    
    @NotBlank(message = "GitHub URL is required")
    private String github;
    
    @NotBlank(message = "Portfolio URL is required")
    private String portfolio;
    
    @NotBlank(message = "Summary is required")
    @Size(max = 1000, message = "Summary must not exceed 1000 characters")
    private String summary;
    
    // Constructors
    public PersonalInfo() {}
    
    public PersonalInfo(String name, String title, String email, String phone, 
                       String location, String linkedin, String github, 
                       String portfolio, String summary) {
        this.name = name;
        this.title = title;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.linkedin = linkedin;
        this.github = github;
        this.portfolio = portfolio;
        this.summary = summary;
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
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getLinkedin() {
        return linkedin;
    }
    
    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }
    
    public String getGithub() {
        return github;
    }
    
    public void setGithub(String github) {
        this.github = github;
    }
    
    public String getPortfolio() {
        return portfolio;
    }
    
    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }
    
    public String getSummary() {
        return summary;
    }
    
    public void setSummary(String summary) {
        this.summary = summary;
    }
}
