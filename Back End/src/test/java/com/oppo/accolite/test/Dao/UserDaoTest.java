package com.oppo.accolite.test.Dao;

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
import com.oppo.accolite.dao.UserDaoImp;
import com.oppo.accolite.models.Audit;
import com.oppo.accolite.models.User;
import com.oppo.accolite.rowmapper.AuditRowMapper;
import com.oppo.accolite.rowmapper.UserRowMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
	
	@Mock
    JdbcTemplate jdbcTemplate;
	
	@Mock
	AuditDaoImp auditDaoImp;
	
	@InjectMocks
	UserDaoImp	userDaoImp;
	
	@Test
	public void shouldAddUser() {
		ArrayList<User> list = new  ArrayList<>();
		list.add(new User("Rishipal Singh", "95rishipal@gmail.com", "", "abc", ""));
		
		Mockito.when(jdbcTemplate.query(
			    Mockito.anyString(),  
			    Mockito.any(UserRowMapper.class))).thenReturn(list);
		List<User> list2 = userDaoImp.getAllUsers();
		Assert.assertEquals(list2.size(), list.size());
	}
	
	@Test
	public void shouldInsertUser() {
		int tomatch = 8;
		Mockito.when(jdbcTemplate.update(
				Mockito.anyString(),
				(Object[])Mockito.anyVararg()
				)).thenReturn(tomatch);
		int result = userDaoImp.insertUser(new User(), "");
		Assert.assertEquals(result, tomatch);
	}
	
	@Test
	public void shouldUpdateToken() {
		int tomatch = 8;
		Mockito.when(jdbcTemplate.update(
				Mockito.anyString()
				)).thenReturn(tomatch);
		int result = userDaoImp.updateToken(new User(), "");
		Assert.assertEquals(result, tomatch);
	}
	
	@Test
	public void shouldFindUserByEmail() {
		User user = new User("Rishipal Singh", "95rishipal@gmail.com", "", "abc", "");
		Mockito.when(jdbcTemplate.queryForObject(
				Mockito.anyString(),
				Mockito.any(UserRowMapper.class)
				)).thenReturn(user);
		User user2 = userDaoImp.findByEmail("");
		Assert.assertEquals(user, user2);
		
	}
}
