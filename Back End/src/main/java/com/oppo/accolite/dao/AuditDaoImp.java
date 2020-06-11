package com.oppo.accolite.dao;

import java.util.Collections;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oppo.accolite.Exception.GlobalException;
import com.oppo.accolite.controller.AuditController;
import com.oppo.accolite.controller.UserController;
import com.oppo.accolite.models.Audit;
import com.oppo.accolite.models.User;
import com.oppo.accolite.rowmapper.AuditRowMapper;

@Repository("AuditDAO")
@Transactional
public class AuditDaoImp implements AuditDao{
	
	public AuditDaoImp(){}
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	UserDaoImp userdao;
	
	@Override
	public List<Audit> getAllAudit() {
		List<Audit> list = jdbcTemplate.query("SELECT * FROM audit", new AuditRowMapper());
		Collections.reverse(list);
		return list;
	}

	@Override
	public int insertAudit(Audit ele, String email) {
		User user = userdao.findByEmail(email);
		String insertSQL = "INSERT INTO audit (date, new_values, old_values, operation, type, user_id, user_name) VALUES (?, ?, ?, ?, ?, ?, ?)";
		System.out.println(user);
		int result = jdbcTemplate.update(insertSQL, new Object[]{ele.getDate(), ele.getNewValues(), ele.getOldValues(), ele.getOperation(), ele.getType(), user.getUserid(), user.getName()}); 
		System.out.print(result);
		return result;
	}

	@Override
	public List<Audit> search(String col, String place) {
		List<Audit> list = null;
		String query = null;
		if(col.equals("date"))  query = "SELECT * FROM audit WHERE date LIKE '%"+place+"%';"; 
		else if(col.equals("operation")) query = "SELECT * FROM audit  WHERE operation LIKE '%"+place+"%';";
		else if(col.equals("userid"))  query = "SELECT * FROM audit  WHERE user_id LIKE '%"+place+"%';";
		else if(col.equals("username")) query = "SELECT * FROM audit  WHERE user_name LIKE '%"+place+"%';";
		else if(col.equals("type"))  query = "SELECT * FROM audit  WHERE type LIKE '%"+place+"%';";
		else query = null;
		if(query != null) {
			list = jdbcTemplate.query(query, new AuditRowMapper());
		}
		return list;
	}
	
	
	
}
