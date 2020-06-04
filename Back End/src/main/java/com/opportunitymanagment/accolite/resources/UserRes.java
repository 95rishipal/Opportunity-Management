package com.opportunitymanagment.accolite.resources;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opportunitymanagment.accolite.models.User;
import com.opportunitymanagment.accolite.repo.UserRepo;

@Controller("user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
	
//	@GetMapping("/user/getall")
//	@ResponseBody
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
	
	@GetMapping("/user/get/{id}" )
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
 	
 	@PostMapping( path = "/user/login", consumes = "application/json", produces = "application/json")
	@ResponseBody
	
	public ResponseEntity  loginUser(@RequestBody User user, @RequestHeader(value = "Token", required=false) String token) {
 		System.out.println(user);
 		System.out.println(token);
 		token = token.substring(0, 20);
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpstatus= HttpStatus.OK;
		User msg=null;
	    try {
	    	User u = userrepo.findByEmail(user.getEmail());
	    	System.out.println(u);
	    	if(!user.getPassword().equals("") && u.getPassword().equals(user.getPassword()) ) {
    				msg = u;
    				BeanUtils.copyProperties(user, user);
    				user.setToken(token);
    				httpstatus= HttpStatus.OK;
	    	}else if(user.getGid().equals("")) {
		    		 httpstatus= HttpStatus.CONFLICT;
		    }else {
		    			if(u != null && u.getGid().equals(user.getGid())) {
		    				msg = u;
		    				BeanUtils.copyProperties(user, user);
		    				user.setToken(token);
		    				httpstatus= HttpStatus.OK;
		    			}else{
		    				user.setToken(token);
		    				userrepo.save(user);
		    				httpstatus= HttpStatus.OK;
		    			}
		    	}
	    	
	    	
		}catch(Exception e) {
			responseHeaders.set("Error-MSG",e.getClass().getSimpleName());
			httpstatus = HttpStatus.CONFLICT;
		}
	    ResponseEntity responseEntity = new ResponseEntity(msg,responseHeaders,httpstatus);
		return responseEntity;
	}
 	
 	@PostMapping(path = "/user/check")
	@ResponseBody
	public ResponseEntity Check(@RequestHeader(value = "Token", required=true) String token, @RequestHeader(value = "Email", required=true) String email) {
 		System.out.println(email);
 		System.out.println(token);
 		token = token.substring(0, 20);
 		HttpStatus httpstatus=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		if(check(email,token)) { httpstatus= HttpStatus.OK; }
		else {
			httpstatus =HttpStatus.NOT_FOUND;
		}
	    ResponseEntity responseEntity = new ResponseEntity("",responseHeaders,httpstatus);
		return responseEntity;
	}
 	
 	
 	
 	public boolean check(String email, String token) {
 		
 		User t = userrepo.findByEmail(email);
 		System.out.println(t);
 		if(t.getToken().equals(token.substring(0, 20))) return true;
 		return false;
 	}
 	
}
