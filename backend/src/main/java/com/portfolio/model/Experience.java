package com.portfolio.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;

@Document(collection = "experience")
public class Experience {
    
    @Id
    private String id;
    
    @NotBlank(message = "Job title is required")
    private String jobTitle;
    
    @NotBlank(message = "Company is required")
    private String company;
    
    @NotBlank(message = "Location is required")
    private String location;
    
    @NotBlank(message = "Start date is required")
    private String startDate;
    
    @NotBlank(message = "End date is required")
    private String endDate;
    
    private boolean isCurrent;
    private String description;
    private List<String> responsibilities;
    private List<String> technologies;
    private String type; // Intern, Full-time, Part-time, etc.
    
    // Constructors
    public Experience() {}
    
    public Experience(String jobTitle, String company, String location, String startDate, String endDate, 
                    boolean isCurrent, String description, List<String> responsibilities, List<String> technologies, String type) {
        this.jobTitle = jobTitle;
        this.company = company;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isCurrent = isCurrent;
        this.description = description;
        this.responsibilities = responsibilities;
        this.technologies = technologies;
        this.type = type;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getJobTitle() {
        return jobTitle;
    }
    
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    
    public String getCompany() {
        return company;
    }
    
    public void setCompany(String company) {
        this.company = company;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getStartDate() {
        return startDate;
    }
    
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    
    public String getEndDate() {
        return endDate;
    }
    
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    public boolean isCurrent() {
        return isCurrent;
    }
    
    public void setCurrent(boolean current) {
        isCurrent = current;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<String> getResponsibilities() {
        return responsibilities;
    }
    
    public void setResponsibilities(List<String> responsibilities) {
        this.responsibilities = responsibilities;
    }
    
    public List<String> getTechnologies() {
        return technologies;
    }
    
    public void setTechnologies(List<String> technologies) {
        this.technologies = technologies;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
}



