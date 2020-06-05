package com.opportunitymanagment.accolite.jdbc.test;

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
import org.mockito.Mockito;
import org.springframework.http.MediaType;
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


@RunWith(SpringJUnit4ClassRunner.class)
public class OpportunityJDBCTest {
//	------------------------- Create Variables For Mocking -------------------------------------
	private MockMvc mockmvc;
	
	
	@InjectMocks
	private OpportunityJDBCTemplate opportunity;
	
	@Before
	public void setUp() throws Exception{	
		 OpportunityJDBCTemplate mockTemplate = Mockito.mock(OpportunityJDBCTemplate.class);
		 mockmvc = MockMvcBuilders.standaloneSetup(mockTemplate).build(); // MVC object
	}
	
	@Test
	public void getAllTest() throws Exception {
		MvcResult result = mockmvc.perform(get("/oppo/getall").header("Email", "95rishipal@gmail.com").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		System.out.println("[Result:]" + result.getResponse().getContentAsString());
			
	}	
	
	
	
}
