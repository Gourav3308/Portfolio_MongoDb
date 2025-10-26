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

import com.portfolio.model.Skill;
import com.portfolio.repository.SkillRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/skills")
@CrossOrigin(origins = "*")
public class SkillController {
    
    @Autowired
    private SkillRepository skillRepository;
    
    @GetMapping
    public ResponseEntity<List<Skill>> getAllSkills() {
        List<Skill> skills = skillRepository.findAll();
        return ResponseEntity.ok(skills);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Skill> getSkillById(@PathVariable String id) {
        Optional<Skill> skill = skillRepository.findById(id);
        return skill.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Skill>> getSkillsByCategory(@PathVariable String category) {
        List<Skill> skills = skillRepository.findByCategory(category);
        return ResponseEntity.ok(skills);
    }
    
    @GetMapping("/proficiency/{minProficiency}")
    public ResponseEntity<List<Skill>> getSkillsByMinProficiency(@PathVariable int minProficiency) {
        List<Skill> skills = skillRepository.findByProficiencyGreaterThan(minProficiency);
        return ResponseEntity.ok(skills);
    }
    
    @PostMapping
    public ResponseEntity<Skill> createSkill(@Valid @RequestBody Skill skill) {
        Skill savedSkill = skillRepository.save(skill);
        return ResponseEntity.ok(savedSkill);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Skill> updateSkill(@PathVariable String id, 
                                           @Valid @RequestBody Skill skill) {
        if (!skillRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        skill.setId(id);
        Skill updatedSkill = skillRepository.save(skill);
        return ResponseEntity.ok(updatedSkill);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable String id) {
        if (!skillRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        skillRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


