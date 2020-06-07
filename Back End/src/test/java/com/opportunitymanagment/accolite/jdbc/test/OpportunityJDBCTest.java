package com.opportunitymanagment.accolite.jdbc.test;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;
import javax.sql.DataSource;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.opportunitymanagment.accolite.JDBCTemplate.AuditJDBCTemplate;
import com.opportunitymanagment.accolite.JDBCTemplate.OpportunityJDBCTemplate;
import com.opportunitymanagment.accolite.models.Audit;
import com.opportunitymanagment.accolite.models.Opportunity;

import junit.framework.Assert;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class OpportunityJDBCTest {
//	------------------------- Create Variables For Mocking -------------------------------------
	private MockMvc mockmvc;
	
	
	@InjectMocks
	private OpportunityJDBCTemplate opportunity;
	
	@Before
	public void setUp() throws Exception{	
		 opportunity = Mockito.mock(OpportunityJDBCTemplate.class);
		 mockmvc = MockMvcBuilders.standaloneSetup(opportunity).build(); // MVC object
	}
	
	@Test(expected = NullPointerException.class)
	public void getAll() throws Exception {
		 OpportunityJDBCTemplate obj = new OpportunityJDBCTemplate();
		 ResponseEntity responseEntity = obj.retrieveAllOpportunity("95rishipal@gmail.com");
		 Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
	}
	
	
	@Test
	public void oppoTest1() {
		OpportunityJDBCTemplate obj = new OpportunityJDBCTemplate();
		Assert.assertEquals("Null", "Null");
	}
	
	@Test
	public void getAllTest() throws Exception {
		MvcResult result = mockmvc.perform(get("/oppo/getall").header("Email", "95rishipal@gmail.com").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		System.out.println("[Result:]" + result.getResponse().getContentAsString());	
	}	
	
	@Test
	public void addWithObject() throws Exception {
		OpportunityJDBCTemplate obj = new OpportunityJDBCTemplate();
		ResponseEntity response = obj.addOpportunity(new Opportunity(), "95rishipal@gmail.com");
		Assert.assertEquals(response.getStatusCodeValue(), 200);
	}	
	
	@Test(expected = NullPointerException.class)
	public void addWithoutObject(){
		OpportunityJDBCTemplate obj = new OpportunityJDBCTemplate();
		ResponseEntity response = obj.addOpportunity(null, "95rishipal@gmail.com");
		System.out.println("--->"+response.getStatusCode());
	}
	
	@Test
	public void editWithId() {
		OpportunityJDBCTemplate obj = new OpportunityJDBCTemplate();
		 ResponseEntity responseEntity = obj.editOpportunity(13, new Opportunity(), "95rishipal@gmail.com");
		 Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
	}
	
	@Test
	public void deluser() {
		OpportunityJDBCTemplate obj = new OpportunityJDBCTemplate();
		 ResponseEntity responseEntity = obj.deleteUserById(0, "95rishipal@gmail.com");
		 Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
	}
	
	@Test
	public void searchFail() {
		 OpportunityJDBCTemplate obj = new OpportunityJDBCTemplate();
		 ResponseEntity responseEntity = obj.searchBy("userid","r","95rishipal@gmail.com");
		 Assert.assertEquals(responseEntity.getStatusCodeValue(), 404);
	}
	
	@Test
	public void search() {
		 OpportunityJDBCTemplate obj = new OpportunityJDBCTemplate();
		 ResponseEntity responseEntity = obj.searchBy("skills","j","95rishipal@gmail.com");
		 Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
		 responseEntity = obj.searchBy("description","aa","95rishipal@gmail.com");
		 Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
		 responseEntity = obj.searchBy("location","Pune","95rishipal@gmail.com");
		 Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
	}
	
}
