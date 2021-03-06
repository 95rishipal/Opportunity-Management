package com.oppo.accolite.dao;

import java.util.List;

import com.oppo.accolite.Exception.NoRecordFound;
import com.oppo.accolite.models.Opportunity;


public interface OpportunityDao {
	public List<Opportunity> searchBy(String col, String place) throws NoRecordFound;
	
	public int insert(Opportunity ele);
	
	public int update(Opportunity ele);
	
	public int del(int id);

	public List<Opportunity> getAllOppo() throws NoRecordFound;
}
