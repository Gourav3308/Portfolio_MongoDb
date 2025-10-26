package com.portfolio.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.model.Education;

@Repository
public interface EducationRepository extends MongoRepository<Education, String> {
    List<Education> findByDegreeContainingIgnoreCase(String degree);
    List<Education> findByUniversityContainingIgnoreCase(String university);
}



