package com.opportunitymanagement.accolite.dao;

import java.util.List;
import java.util.Map;

public interface TrendDao {
	
	public List<Map<String,String>> getCountLang();
	
	public List<Map<String,String>> getCountSkills();
	
	public List<Map<String,String>> getCountDemand();
	
	public List<Map<String,String>> getCountMinxp();
	
	public List<Map<String,String>> getFlashCards();
}
