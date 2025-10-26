package com.portfolio.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Document(collection = "skills")
public class Skill {
    
    @Id
    private String id;
    
    @NotBlank(message = "Skill name is required")
    private String name;
    
    @Min(value = 1, message = "Proficiency must be at least 1")
    @Max(value = 100, message = "Proficiency must not exceed 100")
    private int proficiency;
    
    @NotBlank(message = "Category is required")
    private String category;
    
    private String icon;
    private String description;
    
    // Constructors
    public Skill() {}
    
    public Skill(String name, int proficiency, String category, String icon, String description) {
        this.name = name;
        this.proficiency = proficiency;
        this.category = category;
        this.icon = icon;
        this.description = description;
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
    
    public int getProficiency() {
        return proficiency;
    }
    
    public void setProficiency(int proficiency) {
        this.proficiency = proficiency;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getIcon() {
        return icon;
    }
    
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}
