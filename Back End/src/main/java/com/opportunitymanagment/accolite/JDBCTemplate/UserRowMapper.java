package com.opportunitymanagment.accolite.JDBCTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.opportunitymanagment.accolite.models.User;

public class UserRowMapper  implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setEmail(rs.getString("email"));
		user.setGid(rs.getString("gid"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		user.setUserid(rs.getInt("userid"));
		user.setToken(rs.getString("token"));
		return user;
	}

}
