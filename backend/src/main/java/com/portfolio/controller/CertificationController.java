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

import com.portfolio.model.Certification;
import com.portfolio.repository.CertificationRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/certifications")
@CrossOrigin(origins = "*")
public class CertificationController {
    
    @Autowired
    private CertificationRepository certificationRepository;
    
    @GetMapping
    public ResponseEntity<List<Certification>> getAllCertifications() {
        List<Certification> certificationList = certificationRepository.findAll();
        return ResponseEntity.ok(certificationList);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Certification> getCertificationById(@PathVariable String id) {
        Optional<Certification> certification = certificationRepository.findById(id);
        return certification.map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Certification>> getCertificationsByStatus(@PathVariable String status) {
        List<Certification> certifications = certificationRepository.findByStatus(status);
        return ResponseEntity.ok(certifications);
    }
    
    @GetMapping("/organization/{organization}")
    public ResponseEntity<List<Certification>> getCertificationsByOrganization(@PathVariable String organization) {
        List<Certification> certifications = certificationRepository.findByIssuingOrganizationContainingIgnoreCase(organization);
        return ResponseEntity.ok(certifications);
    }
    
    @PostMapping
    public ResponseEntity<Certification> createCertification(@Valid @RequestBody Certification certification) {
        Certification savedCertification = certificationRepository.save(certification);
        return ResponseEntity.ok(savedCertification);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Certification> updateCertification(@PathVariable String id, 
                                                           @Valid @RequestBody Certification certification) {
        if (!certificationRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        certification.setId(id);
        Certification updatedCertification = certificationRepository.save(certification);
        return ResponseEntity.ok(updatedCertification);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCertification(@PathVariable String id) {
        if (!certificationRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        certificationRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}



