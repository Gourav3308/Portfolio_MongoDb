package com.portfolio.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.model.Project;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {
    List<Project> findByCategory(String category);
    List<Project> findByIsCurrent(boolean isCurrent);
}


