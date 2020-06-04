package com.opportunitymanagment.accolite.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.opportunitymanagment.accolite.models.Test;


@Repository
public interface TestRepo extends JpaRepository<Test, Integer>  {

}
