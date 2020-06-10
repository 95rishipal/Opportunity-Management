package com.opportunitymanagement.accolite.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;import org.springframework.jdbc.core.RowMapper;
import com.opportunitymanagment.accolite.models.Opportunity;

public class OpportunityRowMapper implements RowMapper<Opportunity>  {

	@Override
	public Opportunity mapRow(ResultSet rs, int rowNum) throws SQLException {
		Opportunity oppo = new Opportunity();
		oppo.setOppid(rs.getInt("oppid"));
		oppo.setDemand(rs.getInt("demand"));
		oppo.setdescription(rs.getString("description"));
		oppo.setEndDate(rs.getDate("end_date").toLocalDate());
		oppo.setLocation(rs.getString("location"));
		oppo.setMinxp(rs.getInt("minxp"));
		oppo.setUserid(rs.getInt("userid"));
		oppo.setSkills(rs.getString("skills"));
		return oppo;
	}

}
