package com.portfolio.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;

@Document(collection = "education")
public class Education {
    
    @Id
    private String id;
    
    @NotBlank(message = "Degree is required")
    private String degree;
    
    @NotBlank(message = "University is required")
    private String university;
    
    @NotBlank(message = "Location is required")
    private String location;
    
    @NotBlank(message = "Start date is required")
    private String startDate;
    
    @NotBlank(message = "End date is required")
    private String endDate;
    
    private String gpa;
    private String description;
    
    // Constructors
    public Education() {}
    
    public Education(String degree, String university, String location, String startDate, String endDate, String gpa, String description) {
        this.degree = degree;
        this.university = university;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.gpa = gpa;
        this.description = description;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getDegree() {
        return degree;
    }
    
    public void setDegree(String degree) {
        this.degree = degree;
    }
    
    public String getUniversity() {
        return university;
    }
    
    public void setUniversity(String university) {
        this.university = university;
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
    
    public String getGpa() {
        return gpa;
    }
    
    public void setGpa(String gpa) {
        this.gpa = gpa;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}



