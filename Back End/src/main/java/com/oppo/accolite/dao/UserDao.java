package com.oppo.accolite.dao;

import java.util.List;

import com.oppo.accolite.models.User;


public interface UserDao {
	public List<User> getAllUsers();
	
	public int insertUser(User u, String token);
	
	public int updateToken(User u, String token);
	
	public User findByEmail(String email);
}
