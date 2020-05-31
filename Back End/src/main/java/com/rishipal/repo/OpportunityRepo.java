package com.rishipal.repo;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rishipal.model.Opportunity;



@Repository
public interface OpportunityRepo extends JpaRepository<Opportunity, Integer> {
	@Query("Select c from Opportunity c where c.location like %?1%")	
	List<Opportunity> findByLocationContaining(String place);
	
	@Query("Select c from opportunity c where c.description like %?1%")
	List<Opportunity> findByDescriptionContaining(String place);
//	
	@Query("Select c from Opportunity c where c.skills like %?1%")	
	List<Opportunity> findBySkillsContaining(String place);
}
