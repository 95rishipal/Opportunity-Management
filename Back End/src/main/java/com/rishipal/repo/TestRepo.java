package com.rishipal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rishipal.model.Test;


@Repository
public interface TestRepo extends JpaRepository<Test, Integer>  {

}
