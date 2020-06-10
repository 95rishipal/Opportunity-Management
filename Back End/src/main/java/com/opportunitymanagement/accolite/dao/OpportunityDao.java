package com.opportunitymanagement.accolite.dao;

import java.util.List;
import com.opportunitymanagment.accolite.models.Opportunity;


public interface OpportunityDao {
	public List<Opportunity> searchBy(String col, String place);
	
	public int insert(Opportunity ele);
	
	public int update(Opportunity ele);
	
	public int del(int id);

	public List<Opportunity> getAllOppo();
}
