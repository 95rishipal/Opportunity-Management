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

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.rishipal.model.Opportunity;
import org.json.*;

@Controller("trends")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Trends {
	private static Connection con;	
	
	public Trends(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/accolitedb","root","12345"); 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}  catch (SQLException e) {
			e.printStackTrace();
		}
 	}
	
//	-------------------- Count of Language ----------------------------------
	
	@GetMapping(path = "/trends/language")
	@ResponseBody
	public ResponseEntity getAlllang() {	
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpstatus= HttpStatus.OK;
		PreparedStatement statement;
		List<Map<String,String>> item = new ArrayList<>();
		try {
				statement =con.prepareStatement("SELECT location, count(*) FROM opportunity GROUP BY location; ");
				ResultSet rs = statement.executeQuery();
				while(rs.next()) {  
					Map<String,String> temp = new HashMap<>();
					System.out.println(rs.getString(1)+" --> "+ rs.getString(2));
					temp.put("name",rs.getString(1));
					temp.put("value", rs.getString(2));
					item.add(temp);
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 ResponseEntity responseEntity = new ResponseEntity(item,responseHeaders,httpstatus);
		 return responseEntity;
	}
//	---------------- Count Skills -----------------------------------
	@GetMapping(path = "/trends/skills")
	@ResponseBody
	public ResponseEntity getAllskills() {	
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpstatus= HttpStatus.OK;
		PreparedStatement statement;
		List<Map<String,String>> item = new ArrayList<>();
		try {
				statement =con.prepareStatement("SELECT skills, count(*) FROM opportunity GROUP BY skills; ");
				ResultSet rs = statement.executeQuery();
				while(rs.next()) {  
					Map<String,String> temp = new HashMap<>();
					System.out.println(rs.getString(1)+" --> "+ rs.getString(2));
					temp.put("name",rs.getString(1));
					temp.put("value", rs.getString(2));
					item.add(temp);
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 ResponseEntity responseEntity = new ResponseEntity(item,responseHeaders,httpstatus);
		 return responseEntity;
	}

//	---------------- Count Demand -----------------------------------
	@GetMapping(path = "/trends/demand")
	@ResponseBody
	public ResponseEntity getAllDemands() {	
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpstatus= HttpStatus.OK;
		PreparedStatement statement;
		List<Map<String,String>> item = new ArrayList<>();
		try {
				statement =con.prepareStatement("SELECT demand, count(*) FROM opportunity GROUP BY demand; ");
				ResultSet rs = statement.executeQuery();
				while(rs.next()) {  
					Map<String,String> temp = new HashMap<>();
					System.out.println(rs.getString(1)+" --> "+ rs.getString(2));
					temp.put("name",rs.getString(1));
					temp.put("value", rs.getString(2));
					item.add(temp);
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 ResponseEntity responseEntity = new ResponseEntity(item,responseHeaders,httpstatus);
		 return responseEntity;
	}
//	---------------- Count Min. Exp -----------------------------------
	@GetMapping(path = "/trends/minxp")
	@ResponseBody
	public ResponseEntity getAllminxp() {	
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpstatus= HttpStatus.OK;
		PreparedStatement statement;
		List<Map<String,String>> item = new ArrayList<>();
		try {
				statement =con.prepareStatement("SELECT minxp, count(*) FROM opportunity GROUP BY minxp; ");
				ResultSet rs = statement.executeQuery();
				while(rs.next()) {  
					Map<String,String> temp = new HashMap<>();
					System.out.println(rs.getString(1)+" --> "+ rs.getString(2));
					temp.put("name",rs.getString(1));
					temp.put("value", rs.getString(2));
					item.add(temp);
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 ResponseEntity responseEntity = new ResponseEntity(item,responseHeaders,httpstatus);
		 return responseEntity;
	}	
	
//	------------------- Total Number of Users ----------------------------
	@GetMapping(path = "/trends/countusers")
	@ResponseBody
	public ResponseEntity getcountUsers() {	
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpstatus= HttpStatus.OK;
		PreparedStatement statement;
		ResultSet rs;
		Map<String,String> temp = new HashMap<>();
		List<Map<String,String>> item = new ArrayList<>();
		try {
				statement =con.prepareStatement("SELECT count(*) FROM user; ");
				rs = statement.executeQuery();
				rs.next();
				System.out.println(rs.getString(1));
				temp.put("name","#Users");
				temp.put("value", rs.getString(1));
				item.add(temp);
				temp = null;
				temp = new HashMap<>();
				statement =con.prepareStatement("SELECT COUNT(DISTINCT skills) FROM opportunity; ");
				rs = statement.executeQuery();
				rs.next();
				System.out.println(rs.getString(1));
				temp.put("name","#Skills");
				temp.put("value", rs.getString(1));
				item.add(temp);
				temp = null;
				temp = new HashMap<>();
				statement =con.prepareStatement("SELECT COUNT(*) FROM opportunity; ");
				rs = statement.executeQuery();
				rs.next();
				System.out.println(rs.getString(1));
				temp.put("name","#Opportunities");
				temp.put("value", rs.getString(1));
				item.add(temp);
				temp = null;
				temp = new HashMap<>();
				statement =con.prepareStatement("SELECT COUNT(DISTINCT location) FROM opportunity;  ");
				rs = statement.executeQuery();
				rs.next();
				System.out.println(rs.getString(1));
				temp.put("name","#Locations");
				temp.put("value", rs.getString(1));
				item.add(temp);
				temp = null;
				temp = new HashMap<>();
				
				
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 ResponseEntity responseEntity = new ResponseEntity(item,responseHeaders,httpstatus);
		 return responseEntity;
	}
	
}
