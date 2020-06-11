package com.oppo.accolite.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
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
import com.oppo.accolite.controller.TrendsController;
import com.oppo.accolite.dao.TrendDaoImp;
import com.oppo.accolite.dao.UserDaoImp;
import com.oppo.accolite.models.User;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {TrendsController.class})
public class TrendTest {
	
	@Autowired
	public MockMvc mockMvc;
	
	@MockBean
	public TrendDaoImp trendDaoImp;
	
	@MockBean
	public UserDaoImp userDaoImp;
	
	@Before
	public void setup(){
		
	}
	
	@Test
	public void shouldGetLang() throws Exception {
		Mockito.when(trendDaoImp.getCountLang()).thenReturn(new ArrayList<Map<String,String>>());
		mockMvc.perform(get("/trends/language")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldGetDemand() throws Exception {
		Mockito.when(trendDaoImp.getCountDemand()).thenReturn(new ArrayList<Map<String,String>>());
		mockMvc.perform(get("/trends/demand")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldGetMinXp() throws Exception {
		Mockito.when(trendDaoImp.getCountMinxp()).thenReturn(new ArrayList<Map<String,String>>());
		mockMvc.perform(get("/trends/minxp")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldGetSkills() throws Exception {
		Mockito.when(trendDaoImp.getCountSkills()).thenReturn(new ArrayList<Map<String,String>>());
		mockMvc.perform(get("/trends/skills")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldGetFlashCard() throws Exception {
		Mockito.when(trendDaoImp.getFlashCards()).thenReturn(new ArrayList<Map<String,String>>());
		mockMvc.perform(get("/trends/countusers")).andExpect(status().isOk());
	}
	
}
