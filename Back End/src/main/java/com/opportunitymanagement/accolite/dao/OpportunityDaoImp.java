package com.opportunitymanagement.accolite.dao;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opportunitymanagement.accolite.rowmapper.AuditRowMapper;
import com.opportunitymanagement.accolite.rowmapper.OpportunityRowMapper;
import com.opportunitymanagment.accolite.controller.OpportunityController;
import com.opportunitymanagment.accolite.controller.UserController;
import com.opportunitymanagment.accolite.models.Audit;
import com.opportunitymanagment.accolite.models.Opportunity;
import com.opportunitymanagment.accolite.models.User;


@Repository("OpportunityDao")
@Transactional
public class OpportunityDaoImp implements OpportunityDao {
	
	public OpportunityDaoImp(){}
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	UserDaoImp userDaoImp;

	@Override
	public List<Opportunity> getAllOppo() {
		List<Opportunity> list = jdbcTemplate.query("SELECT * FROM opportunity", new OpportunityRowMapper());
		Collections.reverse(list);
		return list;
	}

	@Override
	public List<Opportunity> searchBy(String col, String place) {
		List<Opportunity> list = null;
		String query = null;
		if(col.equals("description"))  query = "SELECT * FROM opportunity WHERE description LIKE '%"+place+"%';";
		if(col.equals("location")) query = "SELECT * FROM opportunity  WHERE location LIKE '%"+place+"%';";
		if(col.equals("skills"))  query = "SELECT * FROM opportunity  WHERE skills LIKE '%"+place+"%';";
		if(query != null){
			list = jdbcTemplate.query(query, new OpportunityRowMapper());
		}
		return list;
	}

	@Override
	public int insert(Opportunity ele) {
		String insertSQL = "INSERT INTO opportunity (description, end_Date, location, skills, userid, demand, minxp) VALUES (?, ?, ?, ?, ?, ?, ?);";
		int index = jdbcTemplate.update(insertSQL, new Object[]{ele.getdescription(), ele.getEndDate(), ele.getLocation(), ele.getSkills(), ele.getUserid(), ele.getDemand(), ele.getMinxp()});
		return index;
	}

	@Override
	public int update(Opportunity ele) {
		String updateSQL = "UPDATE opportunity SET  description=?, end_Date=?, location=?, skills=?, demand=?, minxp=? WHERE oppid=?";
		int index = jdbcTemplate.update(updateSQL, new Object[]{ele.getdescription(), ele.getEndDate(), ele.getLocation(), ele.getSkills(), ele.getDemand(), ele.getMinxp(), ele.getOppid()});
		return index;
	}

	@Override
	public int del(int id) {
		String updateSQL = "DELETE FROM opportunity WHERE oppid =?";
		int index = jdbcTemplate.update(updateSQL, new Object[]{id});
		return index;
	}
	
	
}
