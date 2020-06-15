package com.oppo.accolite.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

import com.oppo.accolite.Exception.NoRecordFound;


@Repository("TrendDao")
@Transactional
public class TrendDaoImp implements TrendDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public List<Map<String, String>> getCountLang() throws NoRecordFound {
		String Query="SELECT location, count(*) FROM opportunity GROUP BY location; ";
		List<Map<String,String>> item = new ArrayList<>();
		try {
		jdbcTemplate.query(Query, new RowCallbackHandler() {
			public void processRow(ResultSet resultSet) throws SQLException {
				while (resultSet.next()) {
					Map<String,String> temp = new HashMap<>();
					temp.put("name",resultSet.getString(1));
					temp.put("value", resultSet.getString(2));
					item.add(temp);
				}
				Collections.sort(item, new Comparator<Map<String, String>>() {

					@Override
					public int compare(Map<String, String> o1, Map<String, String> o2) {
						String v1 = o1.get("name").toLowerCase();
						String v2 = o2.get("name").toLowerCase();
						return v1.compareTo(v2);
					}
					
				});
				
			}
		});
		}catch(Exception e) {
			throw (NoRecordFound)e;
		}
		return item;
	}

	@Override
	public List<Map<String, String>> getCountSkills() throws NoRecordFound {
		String Query="SELECT skills, count(*) FROM opportunity GROUP BY skills;";
		List<Map<String,String>> item = new ArrayList<>();
		try {
		jdbcTemplate.query(Query, new RowCallbackHandler() {
			public void processRow(ResultSet resultSet) throws SQLException {
				while (resultSet.next()) {
					Map<String,String> temp = new HashMap<>();
					temp.put("name",resultSet.getString(1));
					temp.put("value", resultSet.getString(2));
					item.add(temp);
				}
				Collections.sort(item, new Comparator<Map<String, String>>() {

					@Override
					public int compare(Map<String, String> o1, Map<String, String> o2) {
						String v1 = o1.get("name").toLowerCase();
						String v2 = o2.get("name").toLowerCase();
						return v1.compareTo(v2);
					}
					
				});
				
			}
		});
		}catch(Exception e) {
			throw (NoRecordFound)e;
		}
		return item;
	}

	@Override
	public List<Map<String, String>> getCountDemand() throws NoRecordFound {
		String Query="SELECT demand, count(*) FROM opportunity GROUP BY demand; ";
		List<Map<String,String>> item = new ArrayList<>();
		try {
		jdbcTemplate.query(Query, new RowCallbackHandler() {
			public void processRow(ResultSet resultSet) throws SQLException {
				while (resultSet.next()) {
					Map<String,String> temp = new HashMap<>();
					temp.put("name",resultSet.getString(1));
					temp.put("value", resultSet.getString(2));
					item.add(temp);
				}
				Collections.sort(item, new Comparator<Map<String, String>>() {

					@Override
					public int compare(Map<String, String> o1, Map<String, String> o2) {
						int v1 = Integer.parseInt(o1.get("name"));
						int v2 = Integer.parseInt(o2.get("name"));
						if(v1>v2) return 1;
						else if(v1<v2) return -1;
						return 0;
					}
					
				});
			}
		});
		}catch(Exception e) {
			throw (NoRecordFound)e;
		}
		return item;
	}

	@Override
	public List<Map<String, String>> getCountMinxp() throws NoRecordFound {
		String Query="SELECT minxp, count(*) FROM opportunity GROUP BY minxp; ";
		List<Map<String,String>> item = new ArrayList<>();
		try {
		jdbcTemplate.query(Query, new RowCallbackHandler() {
			public void processRow(ResultSet resultSet) throws SQLException {
				while (resultSet.next()) {
					Map<String,String> temp = new HashMap<>();
					temp.put("name",resultSet.getString(1));
					temp.put("value", resultSet.getString(2));
					item.add(temp);
				}
				
				Collections.sort(item, new Comparator<Map<String, String>>() {

					@Override
					public int compare(Map<String, String> o1, Map<String, String> o2) {
						int v1 = Integer.parseInt(o1.get("name"));
						int v2 = Integer.parseInt(o2.get("name"));
						if(v1>v2) return 1;
						else if(v1<v2) return -1;
						return 0;
					}
					
				});
			}
		});
		}catch(Exception e) {
			throw (NoRecordFound)e;
		}
		return item;
	}

	@Override
	public List<Map<String, String>> getFlashCards() throws NoRecordFound {
		List<Map<String,String>> item = new ArrayList<>();
		String Query = null;
		try {
		Query="SELECT count(*) FROM user; ";
		jdbcTemplate.query(Query, new RowCallbackHandler() {
			public void processRow(ResultSet resultSet) throws SQLException {
					Map<String,String> temp = new HashMap<>();
					temp.put("name","#Users");
					temp.put("value", resultSet.getString(1));
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
		}catch(Exception e) {
			throw (NoRecordFound)e;
		}
		return item;
	}

}
