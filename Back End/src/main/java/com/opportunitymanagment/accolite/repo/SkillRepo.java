package com.opportunitymanagment.accolite.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.opportunitymanagment.accolite.models.Skill;

@Repository
public interface SkillRepo extends JpaRepository<Skill, Integer> {

}
