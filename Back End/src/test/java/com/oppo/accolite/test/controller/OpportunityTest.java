package com.oppo.accolite.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oppo.accolite.controller.OpportunityController;
import com.oppo.accolite.dao.AuditDaoImp;
import com.oppo.accolite.dao.OpportunityDaoImp;
import com.oppo.accolite.dao.UserDaoImp;
import com.oppo.accolite.models.Audit;
import com.oppo.accolite.models.Opportunity;
import com.oppo.accolite.models.User;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {OpportunityController.class})
public class OpportunityTest {
	
	@Autowired
	public MockMvc mockMvc;
	
	@MockBean
	public OpportunityDaoImp opportunityDaoImp;
	
	@MockBean
	public UserDaoImp userDaoImp;
	
	@MockBean
	public AuditDaoImp auditDaoImp;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public Opportunity oppoDefault;
	
	@Before
	public void setup(){
		LocalDate now = LocalDate.now();
		oppoDefault = new Opportunity(0, "Required Java", now , "Pune", "Java", 1, 1, 1);
	}
	
	@Test
	public void shouldGetAllOpportunity() throws Exception {
		Mockito.when(opportunityDaoImp.getAllOppo()).thenReturn(new ArrayList<Opportunity>());
		mockMvc.perform(get("/oppo/getall").header("Email", "95rishipal@gmail.com")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldSearchOpportunity() throws Exception {
		Mockito.when(opportunityDaoImp.searchBy("skills","r")).thenReturn(new ArrayList<Opportunity>());
		mockMvc.perform(get("/oppo/search/skills/r")).andExpect(status().isOk());
	}
	

	@Test
	public void shouldAddOpportunity() throws Exception {
		String json = objectMapper.writeValueAsString(oppoDefault);
		Mockito.when(auditDaoImp.insertAudit(new Audit("Add Opportunity ","Insert", "",json), "95rishipal@gmail.com")).thenReturn(1);
		Mockito.when(opportunityDaoImp.insert(oppoDefault)).thenReturn(1);
		Mockito.when(userDaoImp.findByEmail("95rishipal@gmail.com")).thenReturn(new User(1,"Rishipal Singh","95rishipal@gmail.com","","115640335689848772862","eyJhbGciOiJSUzI1NiIs"));
		mockMvc.perform(post("/oppo/add").contentType(MediaType.APPLICATION_JSON_VALUE).content(json).accept(MediaType.APPLICATION_JSON_VALUE).header("Email", "95rishipal@gmail.com").header("Gid","115640335689848772862")).andExpect(status().isOk());
	}
	

	@Test
	public void shouldEditOpportunity() throws Exception {
		String json = objectMapper.writeValueAsString(oppoDefault);
		Mockito.when(auditDaoImp.insertAudit(new Audit("Add Opportunity ","Insert", "",json), "95rishipal@gmail.com")).thenReturn(1);
		Mockito.when(opportunityDaoImp.update(oppoDefault)).thenReturn(1);
		mockMvc.perform(post("/oppo/edit/12").contentType(MediaType.APPLICATION_JSON_VALUE).content(json).accept(MediaType.APPLICATION_JSON_VALUE).header("Email", "95rishipal@gmail.com").header("Gid","115640335689848772862")).andExpect(status().isOk());
	}
	
	@Test
	public void shoulddelOpportunity() throws Exception {
		String json = objectMapper.writeValueAsString(oppoDefault);
		Mockito.when(auditDaoImp.insertAudit(new Audit("Add Opportunity ","Insert", "",json), "95rishipal@gmail.com")).thenReturn(1);
		Mockito.when(opportunityDaoImp.del(4)).thenReturn(1);
		mockMvc.perform(delete("/oppo/del/4").header("Email", "95rishipal@gmail.com").header("Gid","115640335689848772862")).andExpect(status().isOk());
	}

}
