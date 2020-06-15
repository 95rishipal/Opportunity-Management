package com.oppo.accolite.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.oppo.accolite.Exception.NoRecordFound;
import com.oppo.accolite.dao.AuditDaoImp;
import com.oppo.accolite.dao.OpportunityDaoImp;
import com.oppo.accolite.dao.UserDaoImp;
import com.oppo.accolite.models.Audit;
import com.oppo.accolite.models.Opportunity;
import com.oppo.accolite.models.User;
import org.springframework.http.MediaType;

@Controller
public class OpportunityController {
	
	@Autowired
	OpportunityDaoImp oppoDaoImp;
	
	@Autowired
	AuditDaoImp auditDaoImp;
	
	@Autowired
	UserDaoImp userDaoImp;
	
	private static final Logger logger = LoggerFactory.getLogger(OpportunityController.class);
	
//	------------------------------------------- Get All Opportunities ---------------------------------
	@GetMapping(path = "/oppo/getall")
	@ResponseBody
	public List<Opportunity> getAllOpportunity(@RequestHeader(value = "Email", required=true) String email) {
		logger.info("Opportunity Controller Called-- GetAll");
		List<Opportunity> result = null;
		try {
			auditDaoImp.insertAudit( new Audit("Get All Opportunity","Get All", "",""), email);
			result =  oppoDaoImp.getAllOppo();
		} catch (NoRecordFound e) {
			logger.error("Opportunity Controller Called-- No Record Found");
		}
		return result;
	}
	
	
	@GetMapping(path = "/oppo/search/{col}/{place}")
	@ResponseBody
	public List<Opportunity>  searchBy(@PathVariable("col") String col, @PathVariable("place") String place,@RequestHeader(value = "Email", required=false) String email) {
		logger.info("Opportunity Controller Called-- Search");
		List<Opportunity> result = null;
		try {
				auditDaoImp.insertAudit( new Audit("Search Opportunity Col: "+col+", Value: "+place ,"Search","",""),email);
				result = oppoDaoImp.searchBy(col, place);
		}catch(NoRecordFound e) {
			logger.error("Opportunity Controller Called-- No Record Found");
		}
		return result;
	}
	
	
	@PostMapping(path = "/oppo/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public int  addOpportunity(@RequestBody Opportunity ele,  @RequestHeader(value = "Email", required=false) String email) {
		logger.info("Opportunity Controller Called-- Add Opportunity");
		auditDaoImp.insertAudit( new Audit("Add Opportunity ","Insert", "",ele.toString()),email);
		User user = userDaoImp.findByEmail(email);
		ele.setUserid(user.getUserid());
		return oppoDaoImp.insert(ele);
	}
	
	@PostMapping("/oppo/edit/{id}")
	@ResponseBody
	public int  editOpportunity(@PathVariable("id") int id, @RequestBody Opportunity ele,@RequestHeader(value = "Email", required=false) String email) {
		logger.info("Opportunity Controller Called-- Edit Opportunity");
		auditDaoImp.insertAudit( new Audit("Add Opportunity ","Insert", "",ele.toString()),email);
		return oppoDaoImp.update(ele);
	}
	
	@GetMapping("/oppo/delete/{id}")
	@ResponseBody
	public int deleteUserById(@PathVariable("id") int id,@RequestHeader(value = "Email", required=true) String email) {
		logger.info("Opportunity Controller Called-- Del Opportunity");
		auditDaoImp.insertAudit( new Audit("Delete Opportunity id: "+id ,"Delete","",""),email);
		return oppoDaoImp.del(id);
	}

	
}
