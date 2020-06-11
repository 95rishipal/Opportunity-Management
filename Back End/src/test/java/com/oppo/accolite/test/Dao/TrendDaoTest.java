package com.oppo.accolite.test.Dao;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.test.context.junit4.SpringRunner;

import com.oppo.accolite.dao.AuditDaoImp;
import com.oppo.accolite.dao.OpportunityDaoImp;
import com.oppo.accolite.dao.TrendDaoImp;
import com.oppo.accolite.dao.UserDaoImp;
import com.oppo.accolite.models.Opportunity;
import com.oppo.accolite.rowmapper.OpportunityRowMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrendDaoTest {
	@Mock
    JdbcTemplate jdbcTemplate;
	
	@InjectMocks
	TrendDaoImp trendDaoImp;
	
	@Test(expected = Exception.class)
	public void shouldGetAllOppo() {
		
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		Mockito.doThrow(new Exception()).when(jdbcTemplate).query(
			    Mockito.anyString(),  
			    Mockito.any(RowCallbackHandler.class));
		trendDaoImp.getCountLang();
	}
	
	@Test(expected = Exception.class)
	public void shouldGetAllDemand() {
		
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		Mockito.doThrow(new Exception()).when(jdbcTemplate).query(
			    Mockito.anyString(),  
			    Mockito.any(RowCallbackHandler.class));
		trendDaoImp.getCountDemand();
	}
	
	@Test(expected = Exception.class)
	public void shouldGetAllMinxp() {
		
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		Mockito.doThrow(new Exception()).when(jdbcTemplate).query(
			    Mockito.anyString(),  
			    Mockito.any(RowCallbackHandler.class));
		trendDaoImp.getCountMinxp();
	}
	
	@Test(expected = Exception.class)
	public void shouldGetAllSkill() {
		
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		Mockito.doThrow(new Exception()).when(jdbcTemplate).query(
			    Mockito.anyString(),  
			    Mockito.any(RowCallbackHandler.class));
		trendDaoImp.getCountSkills();
	}
	
	@Test(expected = Exception.class)
	public void shouldGetFlashCards() {
		
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		Mockito.doThrow(new Exception()).when(jdbcTemplate).query(
			    Mockito.anyString(),  
			    Mockito.any(RowCallbackHandler.class));
		trendDaoImp.getFlashCards();
	}
}
