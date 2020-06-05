package com.opportunitymanagment.accolite.JDBCTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.opportunitymanagment.accolite.models.Audit;

public class AuditRowMapper implements RowMapper<Audit> {

	@Override
	public Audit mapRow(ResultSet rs, int rowNum) throws SQLException {
		Audit audit = new Audit();
		audit.setId(rs.getInt("id"));
		audit.setDate(rs.getString("date"));
		audit.setNewValues(rs.getString("new_values"));
		audit.setOldValues(rs.getString("old_values"));
		audit.setOperation(rs.getString("operation"));
		audit.setType(rs.getString("type"));
		audit.setUserId(rs.getInt("user_id"));
		audit.setUserName(rs.getString("user_name"));
		return audit;
	}

}
