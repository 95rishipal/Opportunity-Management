package com.opportunitymanagment.accolite.JDBCTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import com.opportunitymanagment.accolite.models.User;

@Controller("User_JDBC_Template")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserJDBCTemplate {
	public UserJDBCTemplate(){
		template.setDataSource(this.mysqlDataSource());
	}
	
	public JdbcTemplate template = new JdbcTemplate();
	
	public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/accolitedb");
        dataSource.setUsername("root");
        dataSource.setPassword("12345");
        return dataSource;
    }
	
	@GetMapping("/user/getall")
	@ResponseBody
	public ResponseEntity retrieveAllOpportunity(@RequestHeader(value = "Email", required=true) String email) {
		System.out.println(email);
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpstatus= HttpStatus.OK;
		AuditJDBCTemplate obj = new AuditJDBCTemplate();
		List<User> list = obj.template.query("SELECT * FROM user;", new UserRowMapper());
		Map<Integer,User> map = new HashMap<>();
		for(User user : list) {
			map.put(user.getUserid(), user);
		}
		ResponseEntity responseEntity = new ResponseEntity(map,responseHeaders,httpstatus);
		return responseEntity;
	}
	
	@PostMapping( path = "/user/login", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity  loginUser(@RequestBody User user, @RequestHeader(value = "Token", required=false) String token) {
		token = token.substring(0, 20);
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpstatus= HttpStatus.OK;
		String insertSql = "INSERT INTO user ( userid, email, gid, name, password, token) VALUES (?, ?, ?, ?,?,?)";
		User msg=null;
		User u = this.findByEmail(user.getEmail());
		if(u != null && u.getGid().equals(user.getGid())) {
			msg = u;
			this.updateToken(u, token);
		}else {
			msg=user;
			this.insertUser(user,token);
		}
		
		ResponseEntity responseEntity = new ResponseEntity(msg,responseHeaders,httpstatus);
		return responseEntity;
	}
	
	
	@GetMapping("/user/getCurrentUser")
	@ResponseBody
	public User currentUser(@RequestHeader(value = "Email", required=false) String email) {
		System.out.println("[JDBC] User All Request");
		return	this.findByEmail(email); 
	}
	
 	
 	@PostMapping(path = "/user/check")
	@ResponseBody
	public ResponseEntity Check(@RequestHeader(value = "Token", required=true) String token, @RequestHeader(value = "Email", required=true) String email) {
 		token = token.substring(0, 20);
 		HttpStatus httpstatus=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		if(check(email,token)) { httpstatus= HttpStatus.OK; }
		else {
			httpstatus =HttpStatus.NOT_FOUND;
		}
	    ResponseEntity responseEntity = new ResponseEntity("",responseHeaders,httpstatus);
		return responseEntity;
	}
 	
 	public int insertUser(User u, String token) {
 		UserJDBCTemplate obj = new UserJDBCTemplate();
 		String insertSql = "INSERT INTO user (userid, email, gid, name, password, token) VALUES (?,?,?,?,?,?)";
 		return obj.template.update(insertSql, new Object[]{u.getUserid(),u.getEmail(),u.getGid(),u.getName(),u.getPassword(),token}, 
 				new Object[]{Integer.class, String.class, Integer.class, String.class, String.class, String.class});
 	}
 	
 	public int updateToken(User u, String token) {
 		UserJDBCTemplate obj = new UserJDBCTemplate();
 		String updateSql = "UPDATE user SET  token='"+token+"' WHERE userid="+u.getUserid();
 		return obj.template.update(updateSql);	
 	}
 	
 	public User findByEmail(String email) {
 		UserJDBCTemplate obj = new UserJDBCTemplate();
		User u = obj.template.queryForObject("SELECT * FROM user WHERE email='"+email+"';", new UserRowMapper());
		return u;
 	}
 	
 	
 	public boolean check(String email, String token) {
 		User t = this.findByEmail(email);
 
 		if(t.getToken().equals(token.substring(0, 20))) return true;
 		return false;
 	}
}
