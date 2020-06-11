package com.oppo.accolite.test.Dao;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.oppo.accolite.OpportunityManagmenetApplication;
import com.oppo.accolite.dao.AuditDaoImp;
import com.oppo.accolite.dao.UserDaoImp;
import com.oppo.accolite.models.Audit;
import com.oppo.accolite.models.User;
import com.oppo.accolite.rowmapper.AuditRowMapper;
import org.mockito.ArgumentMatchers;
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuditDaoTest {
	
	@Mock
    JdbcTemplate jdbcTemplate;
	
	@InjectMocks
	AuditDaoImp auditDaoImp;
	
	@Mock
	UserDaoImp	userDaoImp;
	
	@Test
	public void shouldAddAudit() {
		ArrayList<Audit> list = new  ArrayList<>();
		list.add(new Audit("Get All", "Get", "", ""));
		
		Mockito.when(jdbcTemplate.query(
			    Mockito.anyString(),  
			    Mockito.any(AuditRowMapper.class))).thenReturn(list);
		List<Audit> list2 = auditDaoImp.getAllAudit();
		Assert.assertEquals(list2.size(), list.size());
	}
	
	@Test
	public void shouldInsertAudit() {
		int tomatch = 8;
		Mockito.when(userDaoImp.findByEmail(Mockito.anyString())).thenReturn(new User());
		Mockito.when(jdbcTemplate.update(
				Mockito.anyString(),
				(Object[])Mockito.anyVararg()
				)).thenReturn(tomatch);
		int result = auditDaoImp.insertAudit(new Audit(), "95rishipal@gmail");
		System.out.println(result);
		Assert.assertEquals(result, tomatch);
	}
	
	@Test
	public void shouldSearchAudit() {
		String query = "SELECT * FROM audit";
		ArrayList<Audit> list = new  ArrayList<>();
		list.add(new Audit("Get All", "Get", "", ""));
		List<Audit> list2 = null;
		Mockito.when(jdbcTemplate.query(
			    Mockito.anyString(),  
			    Mockito.any(AuditRowMapper.class))).thenReturn(list);
		list2 = auditDaoImp.search("operation", "a");
		Assert.assertEquals(list2.size(), list.size());
		list2 = auditDaoImp.search("userid", "a");
		Assert.assertEquals(list2.size(), list.size());
		list2 = auditDaoImp.search("username", "a");
		Assert.assertEquals(list2.size(), list.size());
		list2 = auditDaoImp.search("date", "a");
		Assert.assertEquals(list2.size(), list.size());
		list2 = auditDaoImp.search("type", "a");
		Assert.assertEquals(list2.size(), list.size());
		list2 = auditDaoImp.search("asdasd", "a");
		Assert.assertNull(list2);
	}
	

	
}
