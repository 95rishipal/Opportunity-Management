package com.oppo.accolite.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oppo.accolite.Exception.NoRecordFound;
import com.oppo.accolite.controller.OpportunityController;
import com.oppo.accolite.controller.UserController;
import com.oppo.accolite.models.Audit;
import com.oppo.accolite.models.Opportunity;
import com.oppo.accolite.models.User;
import com.oppo.accolite.rowmapper.AuditRowMapper;
import com.oppo.accolite.rowmapper.OpportunityRowMapper;


@Repository("OpportunityDao")
@Transactional
public class OpportunityDaoImp implements OpportunityDao {
	
	public OpportunityDaoImp(){}
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	UserDaoImp userDaoImp;

	@Override
	public List<Opportunity> getAllOppo() throws NoRecordFound {
		List<Opportunity> list = new ArrayList<>();
		try {
			 list = jdbcTemplate.query("SELECT * FROM opportunity", new OpportunityRowMapper());
			 Collections.reverse(list);
		}catch(Exception e) {
			throw (NoRecordFound)e;
		}
		return list;
	}

	@Override
	public List<Opportunity> searchBy(String col, String place) throws NoRecordFound {
		List<Opportunity> list = null;
		String query = null;
		if(col.equals("description"))  query = "SELECT * FROM opportunity WHERE description LIKE '%"+place+"%';";
		if(col.equals("location")) query = "SELECT * FROM opportunity  WHERE location LIKE '%"+place+"%';";
		if(col.equals("skills"))  query = "SELECT * FROM opportunity  WHERE skills LIKE '%"+place+"%';";
		if(query != null){
			try {
				list = jdbcTemplate.query(query, new OpportunityRowMapper());
			}catch(Exception e) {
				throw (NoRecordFound)e;
			}
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
