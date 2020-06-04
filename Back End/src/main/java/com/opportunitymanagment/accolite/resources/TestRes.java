package com.opportunitymanagment.accolite.resources;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opportunitymanagment.accolite.models.Test;
import com.opportunitymanagment.accolite.repo.TestRepo;


@Controller("test")
public class TestRes {
	@Autowired
	private TestRepo testrepo;
	
	@GetMapping("/test/get")
	@ResponseBody
	public Optional<Test> retrieveAllStudents() {
		return testrepo.findById(1);
	}
	
	@GetMapping("/test/add")
	@ResponseBody
	public String add() {
		Test t = new Test();
		t.setId(3);
		t.setName("Data");
		testrepo.save(t);
		return "Test Added in Table";
	}
	
	
	@GetMapping("/test")
	@ResponseBody
	public String test() {
		return "Test Resource Running!!!";
	}
	
	
}
