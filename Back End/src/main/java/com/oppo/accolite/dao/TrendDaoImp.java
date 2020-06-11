package com.oppo.accolite.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Repository("TrendDao")
@Transactional
public class TrendDaoImp implements TrendDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public List<Map<String, String>> getCountLang() {
		String Query="SELECT location, count(*) FROM opportunity GROUP BY location; ";
		List<Map<String,String>> item = new ArrayList<>();
		jdbcTemplate.query(Query, new RowCallbackHandler() {
			public void processRow(ResultSet resultSet) throws SQLException {
				while (resultSet.next()) {
					Map<String,String> temp = new HashMap<>();
					temp.put("name",resultSet.getString(1));
					temp.put("value", resultSet.getString(2));
					item.add(temp);
				}
			}
		});
		return item;
	}

	@Override
	public List<Map<String, String>> getCountSkills() {
		String Query="SELECT skills, count(*) FROM opportunity GROUP BY skills;";
		List<Map<String,String>> item = new ArrayList<>();
		jdbcTemplate.query(Query, new RowCallbackHandler() {
			public void processRow(ResultSet resultSet) throws SQLException {
				while (resultSet.next()) {
					Map<String,String> temp = new HashMap<>();
					temp.put("name",resultSet.getString(1));
					temp.put("value", resultSet.getString(2));
					item.add(temp);
				}
			}
		});
		return item;
	}

	@Override
	public List<Map<String, String>> getCountDemand() {
		String Query="SELECT demand, count(*) FROM opportunity GROUP BY demand; ";
		List<Map<String,String>> item = new ArrayList<>();
		jdbcTemplate.query(Query, new RowCallbackHandler() {
			public void processRow(ResultSet resultSet) throws SQLException {
				while (resultSet.next()) {
					Map<String,String> temp = new HashMap<>();
					temp.put("name",resultSet.getString(1));
					temp.put("value", resultSet.getString(2));
					item.add(temp);
				}
			}
		});
		return item;
	}

	@Override
	public List<Map<String, String>> getCountMinxp() {
		String Query="SELECT minxp, count(*) FROM opportunity GROUP BY minxp; ";
		List<Map<String,String>> item = new ArrayList<>();
		jdbcTemplate.query(Query, new RowCallbackHandler() {
			public void processRow(ResultSet resultSet) throws SQLException {
				while (resultSet.next()) {
					Map<String,String> temp = new HashMap<>();
					temp.put("name",resultSet.getString(1));
					temp.put("value", resultSet.getString(2));
					item.add(temp);
				}
			}
		});
		return item;
	}

	@Override
	public List<Map<String, String>> getFlashCards() {
		List<Map<String,String>> item = new ArrayList<>();
		String Query = null;
		
		Query="SELECT count(*) FROM user; ";
		jdbcTemplate.query(Query, new RowCallbackHandler() {
			public void processRow(ResultSet resultSet) throws SQLException {
					Map<String,String> temp = new HashMap<>();
					temp.put("name","#Users");
					temp.put("value", resultSet.getString(1));
					System.out.println(resultSet.getString(1));
					item.add(temp);
			}
		});
		
		Query="SELECT COUNT(DISTINCT skills) FROM opportunity; ";
		jdbcTemplate.query(Query, new RowCallbackHandler() {
			public void processRow(ResultSet resultSet) throws SQLException {
			
					Map<String,String> temp = new HashMap<>();
					temp.put("name","#Skills");
					temp.put("value", resultSet.getString(1));
					item.add(temp);
			
			}
		});
		
		Query="SELECT COUNT(*) FROM opportunity; ";
		jdbcTemplate.query(Query, new RowCallbackHandler() {
			public void processRow(ResultSet resultSet) throws SQLException {
				
					Map<String,String> temp = new HashMap<>();
					temp.put("name","#Opportunities");
					temp.put("value", resultSet.getString(1));
					item.add(temp);
			
			}
		});
		
		Query="SELECT COUNT(DISTINCT location) FROM opportunity;  ";
		jdbcTemplate.query(Query, new RowCallbackHandler() {
			public void processRow(ResultSet resultSet) throws SQLException {
				
					Map<String,String> temp = new HashMap<>();
					temp.put("name","#Locations");
					temp.put("value", resultSet.getString(1));
					item.add(temp);
			
			}
		});
		System.out.println(item);
		return item;
	}

}