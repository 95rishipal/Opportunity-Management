package com.rishipal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rishipal.model.Opportunity;


@Repository
public interface OpportunityRepo extends JpaRepository<Opportunity, Integer> {

}
