package com.opportunitymanagement.accolite.dao;

import java.util.Collections;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opportunititymanagement.accolite.Exception.JDBCTemplate;
import com.opportunitymanagement.accolite.rowmapper.AuditRowMapper;
import com.opportunitymanagment.accolite.controller.AuditController;
import com.opportunitymanagment.accolite.controller.UserController;
import com.opportunitymanagment.accolite.models.Audit;
import com.opportunitymanagment.accolite.models.User;

@Repository("AuditDAO")
@Transactional
public class AuditDaoImp implements AuditDao{
	
	public AuditDaoImp(){}
	
	@Autowired(required=true)
	JdbcTemplate jdbcTemplate;
	
	@Autowired(required=true)
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
		return jdbcTemplate.update(insertSQL, new Object[]{ele.getDate(), ele.getNewValues(), ele.getOldValues(), ele.getOperation(), ele.getType(), user.getUserid(), user.getName()});
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
