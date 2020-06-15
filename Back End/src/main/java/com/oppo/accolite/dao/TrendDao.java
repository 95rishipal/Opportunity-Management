package com.oppo.accolite.dao;

import java.util.List;
import java.util.Map;

import com.oppo.accolite.Exception.NoRecordFound;

public interface TrendDao {
	
	public List<Map<String,String>> getCountLang() throws NoRecordFound;
	
	public List<Map<String,String>> getCountSkills() throws NoRecordFound;
	
	public List<Map<String,String>> getCountDemand() throws NoRecordFound;
	
	public List<Map<String,String>> getCountMinxp() throws NoRecordFound;
	
	public List<Map<String,String>> getFlashCards() throws NoRecordFound;
}
