package com.portfolio.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.model.Certification;

@Repository
public interface CertificationRepository extends MongoRepository<Certification, String> {
    List<Certification> findByIssuingOrganizationContainingIgnoreCase(String organization);
    List<Certification> findByStatus(String status);
    List<Certification> findByNameContainingIgnoreCase(String name);
}



