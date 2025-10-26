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

import com.portfolio.model.Education;
import com.portfolio.repository.EducationRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/education")
@CrossOrigin(origins = "http://localhost:4200")
public class EducationController {
    
    @Autowired
    private EducationRepository educationRepository;
    
    @GetMapping
    public ResponseEntity<List<Education>> getAllEducation() {
        List<Education> educationList = educationRepository.findAll();
        return ResponseEntity.ok(educationList);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Education> getEducationById(@PathVariable String id) {
        Optional<Education> education = educationRepository.findById(id);
        return education.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Education> createEducation(@Valid @RequestBody Education education) {
        Education savedEducation = educationRepository.save(education);
        return ResponseEntity.ok(savedEducation);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Education> updateEducation(@PathVariable String id, 
                                                   @Valid @RequestBody Education education) {
        if (!educationRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        education.setId(id);
        Education updatedEducation = educationRepository.save(education);
        return ResponseEntity.ok(updatedEducation);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEducation(@PathVariable String id) {
        if (!educationRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        educationRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}



