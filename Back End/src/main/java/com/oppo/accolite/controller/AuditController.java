package com.oppo.accolite.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.oppo.accolite.dao.AuditDaoImp;
import com.oppo.accolite.models.*;

@RestController("Audit_JDBC_Template")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuditController {

	@Autowired
	AuditDaoImp auditDaoImp;

	@GetMapping(path = "/audit/getall", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public List<Audit> getAllAudit() {
		return auditDaoImp.getAllAudit();
	}
	
	@GetMapping(path = "/audit/search/{col}/{place}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public List<Audit>  searchBy(@PathVariable("col") String col, @PathVariable("place") String place) {
		return auditDaoImp.search(col, place);
	}
	
}
