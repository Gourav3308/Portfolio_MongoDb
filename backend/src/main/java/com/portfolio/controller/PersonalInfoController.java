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

import com.portfolio.model.PersonalInfo;
import com.portfolio.repository.PersonalInfoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/personal-info")
@CrossOrigin(origins = "*")
public class PersonalInfoController {
    
    @Autowired
    private PersonalInfoRepository personalInfoRepository;
    
    @GetMapping
    public ResponseEntity<List<PersonalInfo>> getAllPersonalInfo() {
        List<PersonalInfo> personalInfoList = personalInfoRepository.findAll();
        return ResponseEntity.ok(personalInfoList);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PersonalInfo> getPersonalInfoById(@PathVariable String id) {
        Optional<PersonalInfo> personalInfo = personalInfoRepository.findById(id);
        return personalInfo.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<PersonalInfo> createPersonalInfo(@Valid @RequestBody PersonalInfo personalInfo) {
        PersonalInfo savedPersonalInfo = personalInfoRepository.save(personalInfo);
        return ResponseEntity.ok(savedPersonalInfo);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<PersonalInfo> updatePersonalInfo(@PathVariable String id, 
                                                         @Valid @RequestBody PersonalInfo personalInfo) {
        if (!personalInfoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        personalInfo.setId(id);
        PersonalInfo updatedPersonalInfo = personalInfoRepository.save(personalInfo);
        return ResponseEntity.ok(updatedPersonalInfo);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonalInfo(@PathVariable String id) {
        if (!personalInfoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        personalInfoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


