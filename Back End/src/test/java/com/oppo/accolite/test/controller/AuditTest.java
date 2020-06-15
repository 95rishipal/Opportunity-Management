package com.oppo.accolite.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.oppo.accolite.Exception.NoRecordFound;
import com.oppo.accolite.controller.AuditController;
import com.oppo.accolite.dao.AuditDaoImp;
import com.oppo.accolite.dao.UserDaoImp;
import com.oppo.accolite.models.Audit;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {AuditController.class})
public class AuditTest {

	@Autowired
	public MockMvc mockMvc;
	
	@MockBean
	public UserDaoImp userDaoImp;
	
	@MockBean
	public AuditDaoImp auditDaoImp;
	
	@Before
	public void setup(){
		
	}
	
	@Test
	public void shouldGetLang() throws Exception {
		Mockito.when(auditDaoImp.getAllAudit()).thenReturn(new ArrayList<Audit>());
		mockMvc.perform(get("/audit/getall")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldGetLangCatchException() throws Exception {
		Mockito.when(auditDaoImp.getAllAudit()).thenThrow(NoRecordFound.class);
		mockMvc.perform(get("/audit/getall")).andExpect(status().isOk());
	}
	
	
	
	@Test
	public void shouldSearch() throws Exception {
		Mockito.when(auditDaoImp.search("", "")).thenReturn(new ArrayList<Audit>());
		mockMvc.perform(get("/audit/search/userid/r")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldSearchCatchException() throws Exception {
		Mockito.when(auditDaoImp.search("userid", "r")).thenThrow(NoRecordFound.class);
		mockMvc.perform(get("/audit/search/userid/r")).andExpect(status().isOk());
	}
}
