package com.rishipal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rishipal.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

}
