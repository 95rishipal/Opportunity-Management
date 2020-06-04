package com.opportunitymanagment.accolite.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opportunitymanagment.accolite.models.Opportunity;
import com.opportunitymanagment.accolite.models.User;
import com.opportunitymanagment.accolite.repo.OpportunityRepo;

/*
 * JDBC - Template  
 * 
 */
@Controller("oppo")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OpportunityRes {

	@Autowired
	private OpportunityRepo opportunityrepo;
	
	
//	@GetMapping("/oppo/getall")
	@ResponseBody
	public List<Opportunity> retrieveAllStudents() {
		return opportunityrepo.findAll();
	}
	
//	@GetMapping("/oppo/get/{oppid}")
	@ResponseBody
	public ResponseEntity retrieveUserById(@PathVariable("oppid") int id) {
		Optional<Opportunity> user = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpstatus= HttpStatus.OK;
	    try {
	    	 user = opportunityrepo.findById(id);
		}catch(Exception e) {
			responseHeaders.set("Error-MSG",e.getMessage());
			httpstatus = HttpStatus.CONFLICT;
		}
	    ResponseEntity responseEntity = new ResponseEntity(user,responseHeaders,httpstatus);
		return responseEntity;
	}
	
	
//	@PostMapping(path = "/oppo/add", consumes = "application/json", produces = "application/json")
//	@ResponseBody
	public ResponseEntity addOppo(@RequestBody Opportunity ele) {
		System.out.println(ele);
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpstatus= HttpStatus.OK;
	    try {
	    	opportunityrepo.save(ele);
		}catch(Exception e) {
			responseHeaders.set("Error-MSG",e.getClass().getSimpleName());
			httpstatus = HttpStatus.OK;
		}
	    ResponseEntity responseEntity = new ResponseEntity(ele,responseHeaders,httpstatus);
		return responseEntity;
	}
	
	
//	@DeleteMapping("/oppo/del/{id}")
//	@ResponseBody
	public ResponseEntity deleteUserById(@PathVariable("id") int id) {
		System.out.println(id);
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpstatus= HttpStatus.OK;
	    
	    try {
	    	opportunityrepo.deleteById(id);
		}catch(Exception e) {
			responseHeaders.set("Error-MSG",e.getClass().getSimpleName());
			httpstatus = HttpStatus.CONFLICT;
		}
	    ResponseEntity responseEntity = new ResponseEntity("",responseHeaders,httpstatus);
		return responseEntity;
	}
	
//	@PostMapping("/oppo/edit/{id}")
//	@ResponseBody
	public ResponseEntity  addUser(@PathVariable("id") int id, User user) {
		System.out.println(user);
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
	
	
//	@GetMapping(path = "/oppo/search/{col}/{place}")
	@ResponseBody
	public ResponseEntity  addUser(@PathVariable("col") String col, @PathVariable("place") String place) {
		System.out.println(col+"---"+place);
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpstatus= HttpStatus.NOT_FOUND;
		ArrayList<Opportunity> list = null;
	    try {
			if(col.equals("description")) {
				list = (ArrayList<Opportunity>) opportunityrepo.findByDescriptionContaining(place);
				httpstatus= HttpStatus.OK;
			}
			else if(col.equals("location")) {
				list = (ArrayList<Opportunity>) opportunityrepo.findByLocationContaining(place);
				httpstatus= HttpStatus.OK;
			}else if(col.equals("skills")) {
				list = (ArrayList<Opportunity>) opportunityrepo.findBySkillsContaining(place);
				httpstatus= HttpStatus.OK;
			}
	    	
		}catch(Exception e) {
			responseHeaders.set("Error-MSG",e.getClass().getSimpleName());
			httpstatus = HttpStatus.CONFLICT;
		}
	    ResponseEntity responseEntity = new ResponseEntity(list,responseHeaders,httpstatus);
		return responseEntity;
	}
	
}
