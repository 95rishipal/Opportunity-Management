package com.rishipal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import com.rishipal.model.Opportunity;
import com.rishipal.model.User;

@Controller("User_JDBC")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserJDBC {
//	-------------------- Connector ---------------------------------
	private static Connection con;	
	public UserJDBC(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/accolitedb","root","12345"); 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}  catch (SQLException e) {
			e.printStackTrace();
		}
 	}
	
//	------------------------------------------- Get All Users ---------------------------------
	@GetMapping("/user/getall")
	@ResponseBody
	public Map<Integer, User> retrieveAllOpportunity() {
		System.out.println("[JDBC] User All Request");
		PreparedStatement statement;
		Map<Integer,User> map = new HashMap<>();
//		List<User> list = new ArrayList<>();
		try {
				statement =con.prepareStatement("SELECT * FROM user;");
				ResultSet rs = statement.executeQuery();
				while(rs.next()) {  
					System.out.println(rs.getRow());
					User user = new User(rs.getInt(1), rs.getString(4), rs.getString(2), rs.getString(5), rs.getString(6), rs.getString(3));
//					list.add(user);
					System.out.println(user);
					map.put(user.getUserid(), user);
				}
				return map;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
//	------------------------------------------- Get All Users ---------------------------------
	@GetMapping("/user/getCurrentUser")
	@ResponseBody
	public User currentUser(@RequestHeader(value = "Email", required=false) String email) {
		System.out.println("[JDBC] User All Request");
		PreparedStatement statement;
		User user = null;
		try {
				statement =con.prepareStatement("SELECT * FROM user WHERE email = '"+email+"';");
				ResultSet rs = statement.executeQuery();
				rs.next();
				user = new User(rs.getInt(1), rs.getString(4), rs.getString(2), rs.getString(5), rs.getString(6), rs.getString(3));
				return user;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
