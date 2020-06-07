package com.opportunitymanagment.accolite.jdbc.test;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

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
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.client.MockRestServiceServer.MockRestServiceServerBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.opportunitymanagment.accolite.JDBCTemplate.AuditJDBCTemplate;
import com.opportunitymanagment.accolite.JDBCTemplate.AuditRowMapper;
import com.opportunitymanagment.accolite.JDBCTemplate.OpportunityJDBCTemplate;
import com.opportunitymanagment.accolite.jdbc.*;
import com.opportunitymanagment.accolite.models.Audit;



@RunWith(MockitoJUnitRunner.class)
public class AuditJDBCTest {

//	------------------------- Create Variables For Mocking -------------------------------------
	
	private MockMvc mockmvc;
	
	@InjectMocks
	private AuditJDBCTemplate auditjdbc;
	
	@Mock
    JdbcTemplate jdbcTemplate;
	
	private ArrayList<Audit> getItems(int n){
		ArrayList<Audit> list = new ArrayList<>();
		for(int i=0; i<n; i++) {
			Audit a = new Audit();
			a.setDate("Demo:"+n);
			a.setId(n);
			a.setNewValues("Demo:"+n);
			a.setOldValues("Demo:"+n);
			a.setOperation("Demo:"+n);
			a.setType("Demo:"+n);
			a.setUserName("Demo:"+n);
			a.setUserId(n);
			list.add(a);
		}
		
		return list;
		
	}
	
	
	@Before
	public void setUp() throws Exception{
		auditjdbc = Mockito.mock(AuditJDBCTemplate.class);
		mockmvc = MockMvcBuilders.standaloneSetup(auditjdbc).build(); 
	}
	
//	------------------------- Tests -------------------------------------------
	@Test   // Check the total list received. 
	public void testAuditGetAll() throws Exception{
			Mockito.when(auditjdbc.getAllAudit()).thenReturn(this.getItems(5));
			String result	=	mockmvc.perform(MockMvcRequestBuilders.get("/audit/getall"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.*",Matchers.hasSize(5)))
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andDo(print())
			.andReturn().getResponse().getContentAsString();
			System.out.println("[Result:] "+result);
	}
	
	
	@Test   // Check the total list received. 
	public void getAll() throws Exception{
		AuditJDBCTemplate apple = new AuditJDBCTemplate();
		List<Audit> audit = apple.getAllAudit();
		Assert.assertNotNull(audit);
	}
	
	@Test 
	public void testAuditSearchBy() throws Exception{
			Mockito.when(auditjdbc.getAllAudit()).thenReturn(this.getItems(5));
			mockmvc.perform(MockMvcRequestBuilders.get("/audit/search/k/l").header("Email", "95rishipal@gmail.com"))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test 
	public void testAuditSearch() throws Exception{
		AuditJDBCTemplate apple = new AuditJDBCTemplate();
		ResponseEntity result = apple.searchBy("a", "asd", "95rishipal@gmail.com");
		Assert.assertEquals(result.getStatusCodeValue(), 404);
		result = apple.searchBy("operation", "g", "95rishipal@gmail.com");
		Assert.assertEquals(result.getStatusCodeValue(), 200);
		result = apple.searchBy("userid", "2", "95rishipal@gmail.com");
		Assert.assertEquals(result.getStatusCodeValue(), 200);
		result = apple.searchBy("username", "r", "95rishipal@gmail.com");
		Assert.assertEquals(result.getStatusCodeValue(), 200);
		result = apple.searchBy("type", "i", "95rishipal@gmail.com");
		Assert.assertEquals(result.getStatusCodeValue(), 200);
	}
	
	
	
}
