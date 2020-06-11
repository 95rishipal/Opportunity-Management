package com.oppo.accolite.test.Dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.oppo.accolite.dao.AuditDaoImp;
import com.oppo.accolite.dao.OpportunityDaoImp;
import com.oppo.accolite.dao.UserDaoImp;
import com.oppo.accolite.models.Opportunity;
import com.oppo.accolite.models.User;
import com.oppo.accolite.rowmapper.OpportunityRowMapper;
import com.oppo.accolite.rowmapper.UserRowMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OpportunityDaoTest {
	@Mock
    JdbcTemplate jdbcTemplate;
	
	@Mock
	AuditDaoImp auditDaoImp;
	
	@Mock
	UserDaoImp	userDaoImp;
	
	@InjectMocks
	OpportunityDaoImp oppoDaoImp;
	
	@Test
	public void shouldGetAllOppo() {
		
		ArrayList<Opportunity> list = new  ArrayList<>();
		list.add(new Opportunity(1, "asd",LocalDate.now(), "Pune", "Java",1, 1, 1));
		
		Mockito.when(jdbcTemplate.query(
			    Mockito.anyString(),  
			    Mockito.any(OpportunityRowMapper.class))).thenReturn(list);
		List<Opportunity> list2 = oppoDaoImp.getAllOppo();
		Assert.assertEquals(list2.size(), list.size());
	}
	
	@Test
	public void shouldSearchOppo() {
		ArrayList<Opportunity> list = new  ArrayList<>();
		list.add(new Opportunity(1, "asd",LocalDate.now(), "Pune", "Java",1, 1, 1));
		List<Opportunity> list2=null;
		Mockito.when(jdbcTemplate.query(
			    Mockito.anyString(),  
			    Mockito.any(OpportunityRowMapper.class))).thenReturn(list);
		list2 = oppoDaoImp.searchBy("description", "a");
		Assert.assertEquals(list2.size(), list.size());
		list2 = oppoDaoImp.searchBy("location", "a");
		Assert.assertEquals(list2.size(), list.size());
		list2 = oppoDaoImp.searchBy("skills", "a");
		Assert.assertEquals(list2.size(), list.size());
		list2 = oppoDaoImp.searchBy("asd", "a");
		Assert.assertNull(list2);
	}
	
	@Test
	public void shouldInsertOppo() {
		int tomatch = 8;
		Mockito.when(jdbcTemplate.update(
				Mockito.anyString(),
				(Object[])Mockito.anyVararg()
				)).thenReturn(tomatch);
		int result = oppoDaoImp.insert(new Opportunity());
		Assert.assertEquals(result, tomatch);
	}
	
	@Test
	public void shouldUpdateOppo() {
		int tomatch = 8;
		Mockito.when(jdbcTemplate.update(
				Mockito.anyString(),
				(Object[])Mockito.anyVararg()
				)).thenReturn(tomatch);
		int result = oppoDaoImp.update(new Opportunity());
		Assert.assertEquals(result, tomatch);
	}
	
	@Test
	public void shouldDelOppo() {
		int tomatch = 8;
		Mockito.when(jdbcTemplate.update(
				Mockito.anyString(),
				(Object[])Mockito.anyVararg()
				)).thenReturn(tomatch);
		int result = oppoDaoImp.del(1);
		Assert.assertEquals(result, tomatch);
	}
	
}
