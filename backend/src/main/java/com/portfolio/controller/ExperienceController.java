package com.portfolio.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.model.Experience;
import com.portfolio.repository.ExperienceRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/experience")
@CrossOrigin(origins = "http://localhost:4200")
public class ExperienceController {
    
    @Autowired
    private ExperienceRepository experienceRepository;
    
    @GetMapping
    public ResponseEntity<List<Experience>> getAllExperience() {
        List<Experience> experienceList = experienceRepository.findAll();
        return ResponseEntity.ok(experienceList);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Experience> getExperienceById(@PathVariable String id) {
        Optional<Experience> experience = experienceRepository.findById(id);
        return experience.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/current")
    public ResponseEntity<List<Experience>> getCurrentExperience() {
        List<Experience> currentExperience = experienceRepository.findByIsCurrent(true);
        return ResponseEntity.ok(currentExperience);
    }
    
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Experience>> getExperienceByType(@PathVariable String type) {
        List<Experience> experience = experienceRepository.findByType(type);
        return ResponseEntity.ok(experience);
    }
    
    @PostMapping
    public ResponseEntity<Experience> createExperience(@Valid @RequestBody Experience experience) {
        Experience savedExperience = experienceRepository.save(experience);
        return ResponseEntity.ok(savedExperience);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Experience> updateExperience(@PathVariable String id, 
                                                    @Valid @RequestBody Experience experience) {
        if (!experienceRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        experience.setId(id);
        Experience updatedExperience = experienceRepository.save(experience);
        return ResponseEntity.ok(updatedExperience);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExperience(@PathVariable String id) {
        if (!experienceRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        experienceRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}



