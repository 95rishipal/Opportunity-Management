package com.opportunitymanagment.accolite.jdbc.test;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer.MockRestServiceServerBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.opportunitymanagment.accolite.jdbc.*;
import com.opportunitymanagment.accolite.models.Audit;



@RunWith(SpringJUnit4ClassRunner.class)
public class AuditJDBCTest {

//	------------------------- Create Variables For Mocking -------------------------------------
	
	private MockMvc mockmvc;
	
	@InjectMocks
	private AuditJDBC auditjdbc;
    
//  ------------------------ Setup -----------------------------------------
	public List<Audit> demoObjects(int n){
		List<Audit> list = new ArrayList<>();
		for(int i=0; i<n; i++) {
			Audit audit = new Audit();
			audit.setId(i);
			audit.setDate("Demo Date :"+i);
			audit.setNewValues("Demo New Value:"+i);
			audit.setOldValues("Demo Old Value:"+i);
			audit.setOperation("Demo Old Value:"+i);
			audit.setType("Demo Type:"+i);
			audit.setUserId(0);
			list.add(audit);
		}
		
		return list;
	}
	
	@Before
	public void setUp() throws Exception{	
		 AuditJDBC mockTemplate = Mockito.mock(AuditJDBC.class);
		 List<Audit> mockResult = demoObjects(4);
		 Mockito.when(mockTemplate.retrieveAllOpportunity()).thenReturn(mockResult);
		 mockmvc = MockMvcBuilders.standaloneSetup(mockTemplate).build(); // MVC object
		
	}
	
//	------------------------- Tests -------------------------------------------
	
	@Test // Api: /audit/getall Running
	public void auditRunning() throws Exception {
		mockmvc.perform(MockMvcRequestBuilders.get("/audit/getall"))
			.andExpect(MockMvcResultMatchers.status().isOk());			
	}
	
	
	@Test   // Check the total list received. 
	public void testAuditGetAll() throws Exception{
		System.out.println(mockmvc.perform(MockMvcRequestBuilders.get("/audit/getall"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.*",Matchers.hasSize(4)))
			.andReturn().getResponse().getContentAsString());
	}
	
	
//	
	@Test
	public void testAuditSearch() throws Exception{
	String a = mockmvc.perform(MockMvcRequestBuilders.get("/audit/search/username/d").header("Email", "95rishipal@gmail.com"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andReturn().getResponse().getContentAsString();
	System.out.println("[Result] "+a);
	}
}
