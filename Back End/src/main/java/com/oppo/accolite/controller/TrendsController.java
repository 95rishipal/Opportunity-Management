package com.oppo.accolite.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oppo.accolite.dao.TrendDaoImp;
import com.oppo.accolite.models.Audit;
import com.oppo.accolite.models.Opportunity;


@Controller

public class TrendsController {

	@Autowired
	TrendDaoImp trendDaoImp;
	
//	-------------------- Count of Language ----------------------------------
	
	@GetMapping(path = "/trends/language")
	@ResponseBody
	public List<Map<String, String>> getAlllang() {
		return trendDaoImp.getCountLang();
	}
//	---------------- Count Skills -----------------------------------
	@GetMapping(path = "/trends/skills")
	@ResponseBody
	public  List<Map<String, String>> getAllskills() {	
		return trendDaoImp.getCountSkills();
	}

//	---------------- Count Demand -----------------------------------
	@GetMapping(path = "/trends/demand")
	@ResponseBody
	public  List<Map<String, String>> getAllDemands() {	
		return trendDaoImp.getCountDemand();
	}
//	---------------- Count Min. Exp -----------------------------------
	@GetMapping(path = "/trends/minxp")
	@ResponseBody
	public  List<Map<String, String>> getAllminxp() {	
		return trendDaoImp.getCountMinxp();
	}	
	
//	------------------- Total Number of Users ----------------------------
	@GetMapping(path = "/trends/countusers")
	@ResponseBody
	public  List<Map<String, String>> getcountUsers() {	
		return trendDaoImp.getFlashCards();
	}
	
}
