package com.opportunitymanagment.accolite.JDBCTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opportunitymanagment.accolite.models.Audit;
import com.opportunitymanagment.accolite.models.Opportunity;
import com.opportunitymanagment.accolite.models.User;

@Controller("Opportunity_JDBC_Template")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OpportunityJDBCTemplate {
	public OpportunityJDBCTemplate(){
		template.setDataSource(new Dataservice("mysql").getDataSource());
		audit=new AuditJDBCTemplate();
	}
	
	public JdbcTemplate template = new JdbcTemplate();
	private AuditJDBCTemplate audit = null;
	
	
//	------------------------------------------- Get All Opportunities ---------------------------------
	@GetMapping(path = "/oppo/getall", produces = "application/json")
	@ResponseBody
	public ResponseEntity retrieveAllOpportunity(@RequestHeader(value = "Email", required=true) String email) {
		System.out.println(email);
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpstatus= HttpStatus.OK;
		audit.insertAudit( new Audit("Get All Opportunity","Get All", "",""), email);
		AuditJDBCTemplate obj = new AuditJDBCTemplate();
		List<Opportunity> list = obj.template.query("SELECT * FROM opportunity;", new OpportunityRowMapper());
		for(Opportunity i : list) {
			System.out.println(i);
		}
		ResponseEntity responseEntity = new ResponseEntity(list,responseHeaders,httpstatus);
		return responseEntity;
	}
	
	
	@GetMapping(path = "/oppo/search/{col}/{place}")
	@ResponseBody
	public ResponseEntity  searchBy(@PathVariable("col") String col, @PathVariable("place") String place,@RequestHeader(value = "Email", required=false) String email) {
		audit.insertAudit( new Audit("Search Opportunity Col: "+col+", Value: "+place ,"Search","",""),email);
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpstatus= HttpStatus.NOT_FOUND;
		String query = null;
		List<Opportunity> list = null;

		if(col.equals("description"))  query = "SELECT * FROM opportunity WHERE description LIKE '%"+place+"%';";
		if(col.equals("location")) query = "SELECT * FROM opportunity  WHERE location LIKE '%"+place+"%';";
		if(col.equals("skills"))  query = "SELECT * FROM opportunity  WHERE skills LIKE '%"+place+"%';";
			
		if(query != null){
			OpportunityJDBCTemplate obj = new OpportunityJDBCTemplate();
			list = obj.template.query(query, new OpportunityRowMapper());
			httpstatus= HttpStatus.OK;
		}
		
	    ResponseEntity responseEntity = new ResponseEntity(list,responseHeaders,httpstatus);
		return responseEntity;
	}
	
	
	@PostMapping(path = "/oppo/add", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity  addOpportunity(@RequestBody Opportunity ele,  @RequestHeader(value = "Email", required=false) String email) {
		audit.insertAudit( new Audit("Add Opportunity ","Insert", "",ele.toString()),email);
		OpportunityJDBCTemplate obj = new OpportunityJDBCTemplate();
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpstatus= HttpStatus.OK;
			UserJDBCTemplate userTemplate = new UserJDBCTemplate();
			User user = userTemplate.findByEmail(email);
			String insertSQL = "INSERT INTO opportunity (description, end_Date, location, skills, userid, demand, minxp) VALUES (?, ?, ?, ?, ?, ?, ?);";
			int index = obj.template.update(insertSQL, new Object[]{ele.getdescription(), ele.getEndDate(), ele.getLocation(), ele.getSkills(), user.getUserid(), ele.getDemand(), ele.getMinxp()});
	    ResponseEntity responseEntity = new ResponseEntity(index,responseHeaders,httpstatus);
		return responseEntity;
	}
	
	@PostMapping("/oppo/edit/{id}")
	@ResponseBody
	public ResponseEntity  editOpportunity(@PathVariable("id") int id, @RequestBody Opportunity ele,@RequestHeader(value = "Email", required=false) String email) {
		OpportunityJDBCTemplate obj =  new OpportunityJDBCTemplate();
		audit.insertAudit( new Audit("Add Opportunity ","Insert", "",ele.toString()),email);
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpstatus= HttpStatus.OK;
		UserJDBCTemplate userTemplate = new UserJDBCTemplate();
			User user = userTemplate.findByEmail(email);
			String updateSQL = "UPDATE opportunity SET  description=?, end_Date=?, location=?, skills=? WHERE oppid=?";
			int index = obj.template.update(updateSQL, new Object[]{ele.getdescription(), ele.getEndDate(), ele.getLocation(), ele.getSkills(), ele.getOppid()});
	    ResponseEntity responseEntity = new ResponseEntity(index,responseHeaders,httpstatus);
		return responseEntity;
	}
	
	@DeleteMapping("/oppo/del/{id}")
	@ResponseBody
	public ResponseEntity deleteUserById(@PathVariable("id") int id,@RequestHeader(value = "Email", required=false) String email) {
		audit.insertAudit( new Audit("Delete Opportunity id: "+id ,"Delete","",""),email);
		OpportunityJDBCTemplate obj= new OpportunityJDBCTemplate();
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus httpstatus= HttpStatus.OK;
		String updateSQL = "DELETE FROM opportunity WHERE oppid =?";
		int index = obj.template.update(updateSQL, new Object[]{id});
	    ResponseEntity responseEntity = new ResponseEntity("",responseHeaders,httpstatus);
		return responseEntity;
	}

	
}
