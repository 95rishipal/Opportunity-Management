package com.opportunitymanagment.accolite.JDBCTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import com.opportunitymanagment.accolite.models.*;

import com.opportunitymanagment.accolite.models.Audit;

@Controller("Audit_JDBC_Template")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuditJDBCTemplate {

	public AuditJDBCTemplate(){
		template.setDataSource(new Dataservice("mysql").getDataSource());
	}
	
	public JdbcTemplate template = new JdbcTemplate();


	@GetMapping(path = "/audit/getall", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public List<Audit> getAllAudit() {
		AuditJDBCTemplate obj = new AuditJDBCTemplate();
		List<Audit> list = obj.template.query("SELECT * FROM audit", new AuditRowMapper());
		Collections.reverse(list);
		return list;
	}
	
	@GetMapping(path = "/audit/search/{col}/{place}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ResponseEntity  searchBy(@PathVariable("col") String col, @PathVariable("place") String place,@RequestHeader(value = "Email", required=true) String email) {
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpstatus= HttpStatus.NOT_FOUND;
		String query = null;
		List<Audit> list = null;
		if(col.equals("date"))  query = "SELECT * FROM audit WHERE date LIKE '%"+place+"%';"; 
		else if(col.equals("operation")) query = "SELECT * FROM audit  WHERE operation LIKE '%"+place+"%';";
		else if(col.equals("userid"))  query = "SELECT * FROM audit  WHERE user_id LIKE '%"+place+"%';";
		else if(col.equals("username")) query = "SELECT * FROM audit  WHERE user_name LIKE '%"+place+"%';";
		else if(col.equals("type"))  query = "SELECT * FROM audit  WHERE type LIKE '%"+place+"%';";
		else query = null;
		if(query != null) {
			AuditJDBCTemplate obj = new AuditJDBCTemplate();
			list = obj.template.query(query, new AuditRowMapper());
			httpstatus= HttpStatus.OK;
		}
	    ResponseEntity responseEntity = new ResponseEntity(list,responseHeaders,httpstatus);
		return responseEntity;
	}
	
	
	public int insertAudit(Audit ele, String email) {
		AuditJDBCTemplate  auditTemplate = new AuditJDBCTemplate();
		UserJDBCTemplate userTemplate = new UserJDBCTemplate();
		User user = userTemplate.findByEmail(email);
		String insertSQL = "INSERT INTO audit (date, new_values, old_values, operation, type, user_id, user_name) VALUES (?, ?, ?, ?, ?, ?, ?)";
		return auditTemplate.template.update(insertSQL, new Object[]{ele.getDate(), ele.getNewValues(), ele.getOldValues(), ele.getOperation(), ele.getType(), user.getUserid(), user.getName()});
	}
}
