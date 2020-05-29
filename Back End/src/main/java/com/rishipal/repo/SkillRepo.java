package com.rishipal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rishipal.model.Skill;

@Repository
public interface SkillRepo extends JpaRepository<Skill, Integer> {

}
