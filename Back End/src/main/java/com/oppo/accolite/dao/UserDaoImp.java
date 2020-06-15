package com.oppo.accolite.dao;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oppo.accolite.models.User;
import com.oppo.accolite.rowmapper.UserRowMapper;



@Repository("UserDao")
@Transactional
public class UserDaoImp implements UserDao {
	
	public UserDaoImp(){}
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<User> getAllUsers() {
		List<User> list = jdbcTemplate.query("SELECT * FROM user", new UserRowMapper());
		Collections.reverse(list);
		return list;
	}

	@Override
	public int insertUser(User u, String token) {
		String insertSql = "INSERT INTO user (email, gid, name, password, token) VALUES (?,?,?,?,?)";
 		int a =  jdbcTemplate.update(insertSql, new Object[]{u.getEmail(),u.getGid(),u.getName(),u.getPassword(),token});
 		return a;
	}

	@Override
	public int updateToken(User u, String token) {
		String updateSql = "UPDATE user SET  token='"+token+"' WHERE userid="+u.getUserid();
 		return jdbcTemplate.update(updateSql);	
	}

	@Override
	public User findByEmail(String email) {
		User u = null;
		try {
		u = jdbcTemplate.queryForObject("SELECT * FROM user WHERE email='"+email+"';", new UserRowMapper());
		}catch(Exception e) {

		}
		return u;
	}

}
