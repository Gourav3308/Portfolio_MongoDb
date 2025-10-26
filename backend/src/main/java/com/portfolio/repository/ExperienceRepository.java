package com.portfolio.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.model.Experience;

@Repository
public interface ExperienceRepository extends MongoRepository<Experience, String> {
    List<Experience> findByCompanyContainingIgnoreCase(String company);
    List<Experience> findByJobTitleContainingIgnoreCase(String jobTitle);
    List<Experience> findByIsCurrent(boolean isCurrent);
    List<Experience> findByType(String type);
}



