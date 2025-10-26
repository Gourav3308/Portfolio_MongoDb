package com.portfolio.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.model.Skill;

@Repository
public interface SkillRepository extends MongoRepository<Skill, String> {
    List<Skill> findByCategory(String category);
    List<Skill> findByProficiencyGreaterThan(int proficiency);
}


