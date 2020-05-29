package com.rishipal.resource;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.rishipal.model.User;
import com.rishipal.repo.UserRepo;

@Controller("user")
public class UserRes {
	/*
	 *  		/user/getall 	-> To get all users		-> Admin [Grid]
	 *  		/user/get/{id}	-> Perticular User Info	-> User
	 *  		/user/del/{id}	-> Del User				-> Admin [Grid]
	 *  		/user/add		-> Add New User			-> Admin [Grid]
	 *  			
	 */
	
	@Autowired
	private UserRepo userrepo;
	
	@GetMapping("/user/getall")
	@ResponseBody
	public ResponseEntity retrieveAllUsers() {
		List<User> users = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpstatus= HttpStatus.OK;
	    try {
	    	 users = userrepo.findAll();
		}catch(Exception e) {
			responseHeaders.set("Error-MSG",e.getMessage());
			httpstatus = HttpStatus.CONFLICT;
		}
	    ResponseEntity responseEntity = new ResponseEntity(users,responseHeaders,httpstatus);
		return responseEntity;
	}
	
	@GetMapping("/user/get/{id}")
	@ResponseBody
	public ResponseEntity retrieveUserById(@PathVariable("id") int id) {
		Optional<User> user = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpstatus= HttpStatus.OK;
	    try {
	    	 user = userrepo.findById(id);
		}catch(Exception e) {
			responseHeaders.set("Error-MSG",e.getMessage());
			httpstatus = HttpStatus.CONFLICT;
		}
	    ResponseEntity responseEntity = new ResponseEntity(user,responseHeaders,httpstatus);
		return responseEntity;
	}
	
	
	@GetMapping("/user/del/{id}")
	@ResponseBody
	public ResponseEntity deleteUserById(@PathVariable("id") int id) {
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpstatus= HttpStatus.OK;
	    try {
	    	userrepo.deleteById(id);
		}catch(Exception e) {
			responseHeaders.set("Error-MSG",e.getMessage());
			httpstatus = HttpStatus.CONFLICT;
		}
	    ResponseEntity responseEntity = new ResponseEntity("[User]: Del api",responseHeaders,httpstatus);
		return responseEntity;
	}
	
	
	
	@PostMapping("/user/add")
	@ResponseBody
	public ResponseEntity  addUser(User user) {
		System.out.print(user);
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpstatus= HttpStatus.OK;
	    try {
			userrepo.save(user);
		}catch(Exception e) {
			responseHeaders.set("Error-MSG",e.getClass().getSimpleName());
			httpstatus = HttpStatus.CONFLICT;
		}
	    ResponseEntity responseEntity = new ResponseEntity("[User]: Add api",responseHeaders,httpstatus);
		return responseEntity;
	}
	
	
 	@PostMapping("/user/edit/{id}")
	@ResponseBody
	public ResponseEntity  addUser(@PathVariable("id") int id, User user) {
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpstatus= HttpStatus.OK;
	    try {
			BeanUtils.copyProperties(user, user, "id");
		}catch(Exception e) {
			responseHeaders.set("Error-MSG",e.getClass().getSimpleName());
			httpstatus = HttpStatus.CONFLICT;
		}
	    ResponseEntity responseEntity = new ResponseEntity("[User]: Edit api",responseHeaders,httpstatus);
		return responseEntity;
	}
 	
}
