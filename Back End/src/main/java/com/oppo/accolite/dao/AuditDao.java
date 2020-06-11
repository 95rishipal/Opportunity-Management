package com.oppo.accolite.dao;

import java.util.List;

import com.oppo.accolite.models.Audit;

public interface AuditDao {
	public List<Audit> getAllAudit();
	
	public int insertAudit(Audit ele, String email);
	
	public List<Audit> search(String col, String place);
	
}