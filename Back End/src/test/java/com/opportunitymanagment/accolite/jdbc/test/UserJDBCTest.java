package com.opportunitymanagment.accolite.jdbc.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;

import javax.net.ssl.SSLEngineResult.Status;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.opportunitymanagment.accolite.JDBCTemplate.OpportunityJDBCTemplate;
import com.opportunitymanagment.accolite.JDBCTemplate.UserJDBCTemplate;
import com.opportunitymanagment.accolite.JDBCTemplate.UserRowMapper;
import com.opportunitymanagment.accolite.models.User;

import junit.framework.Assert;

public class UserJDBCTest {
	@InjectMocks
	private UserJDBCTemplate userTemplate;
	
	@Test
	public void getall() {
		JdbcTemplate t = Mockito.mock(JdbcTemplate.class);
		when(t.query("SELECT * FROM user;", new UserRowMapper())).thenReturn(new ArrayList<User>());
		UserJDBCTemplate user = new  UserJDBCTemplate();
		ResponseEntity responseEntity  = user.retrieveAllOpportunity("95rishipal@gmail.com");
		Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
	}
	
	@Test
	public void login() throws Exception {
		String json ="{"
				+ "}";
		UserJDBCTemplate user = Mockito.mock(UserJDBCTemplate.class);
		MockMvc mockmvc = MockMvcBuilders.standaloneSetup(user).build(); // MVC object
		when(user.insertUser(new User(), "")).thenReturn(0);
		when(user.findByEmail("")).thenReturn(new User());
		when(user.updateToken(new User(), (""))).thenReturn(0);
		mockmvc.perform(post("/user/login").contentType("application/json").header("Email", "95rishipal@gmail.com")).andExpect(status().isBadRequest());
	}
	
	@Test(expected = Exception.class)
	public void detail() throws Exception {
		UserJDBCTemplate user = new  UserJDBCTemplate();
		int a = user.insertUser(new User("demo", "demo12@gmail.com","12","123456","01234567890123456789"), "01234567890123456789");	
		Assert.assertEquals(a, 1);
	}
	
	@Test
	public void findByEmail() {
		UserJDBCTemplate user = new  UserJDBCTemplate();
		User responseEntity  = user.findByEmail("95rishipal@gmail.com");
		Assert.assertNotNull(responseEntity);
	}
	
	
	@Test
	public void currUser() {
		UserJDBCTemplate user = new  UserJDBCTemplate();
		User responseEntity  = user.currentUser("95rishipal@gmail.com");
		Assert.assertNotNull(responseEntity);
	}
	
	@Test
	public void check() {
		UserJDBCTemplate user = new  UserJDBCTemplate();
		ResponseEntity responseEntity  = user.Check("eyJhbGciOiJSUzI1NiIs","95rishipal@gmail.com");
		Assert.assertNotNull(responseEntity);
	}
	
	@Test
	public void checkFail() {
		UserJDBCTemplate user = new  UserJDBCTemplate();
		ResponseEntity responseEntity  = user.Check("01234567890123456789","95rishipal@gmail.com");
		Assert.assertNotNull(responseEntity);
	}
	
	@Test
	public void updateBranch() {
		UserJDBCTemplate user = new  UserJDBCTemplate();
		ResponseEntity responseEntity  = user.loginUser(new User(1,"Rishipal Singh","95rishipal@gmail.com", "", "115640335689848772862","eyJhbGciOiJSUzI1NiIs"),"12345678901234567890");
		Assert.assertNotNull(responseEntity);
	}
	
		
}
