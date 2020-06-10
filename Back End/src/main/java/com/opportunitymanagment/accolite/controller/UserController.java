package com.opportunitymanagment.accolite.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opportunitymanagement.accolite.dao.UserDaoImp;
import com.opportunitymanagment.accolite.models.User;

@Controller("User_JDBC_Template")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	
	@Autowired
	UserDaoImp userDaoImp;
	
	@GetMapping("/user/getall")
	@ResponseBody
	public Map<Integer,User> retrieveAllUser() {
		List<User> list =  userDaoImp.getAllUsers();
		Map<Integer,User> map = new HashMap<>();
		for(User user : list) {
			map.put(user.getUserid(), user);
		}
		return map;	
	}
	
	@PostMapping( path = "/user/login", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity  loginUser(@RequestBody User user, @RequestHeader(value = "Token", required=false) String token) {
		System.out.println(user);
		token = token.substring(0, 20);
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpstatus= HttpStatus.OK;
		User msg=null;
		User u = userDaoImp.findByEmail(user.getEmail());
		if(u != null && u.getGid().equals(user.getGid())) {
			msg = u;
			userDaoImp.updateToken(u, token);
		}else {
			msg=user;
			userDaoImp.insertUser(user,token);
		}
		
		ResponseEntity responseEntity = new ResponseEntity(msg,responseHeaders,httpstatus);
		return responseEntity;
	}
	
	
	@GetMapping("/user/getCurrentUser")
	@ResponseBody
	public User currentUser(@RequestHeader(value = "Email", required=false) String email) {
		return	userDaoImp.findByEmail(email); 
	}
	
 	
 	@PostMapping(path = "/user/check")
	@ResponseBody
	public ResponseEntity Check(@RequestHeader(value = "Gid", required=true) String Gid, @RequestHeader(value = "Email", required=true) String email) {
 		HttpStatus httpstatus=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		if(check(email,Gid)) { httpstatus= HttpStatus.OK; }
		else {
			httpstatus =HttpStatus.NOT_FOUND;
		}
	    ResponseEntity responseEntity = new ResponseEntity("",responseHeaders,httpstatus);
		return responseEntity;
	}
 	

 	
 	public boolean check(String email, String Gid) {
 		User t = userDaoImp.findByEmail(email);
 		if(t.getGid().equals(Gid)) return true;
 		return false;
 	}
 	
}
