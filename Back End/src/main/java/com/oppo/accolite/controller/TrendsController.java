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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.oppo.accolite.Exception.NoRecordFound;
import com.oppo.accolite.dao.TrendDaoImp;
import com.oppo.accolite.models.Audit;
import com.oppo.accolite.models.Opportunity;


@Controller

public class TrendsController {

	@Autowired
	TrendDaoImp trendDaoImp;
	
	private static final Logger logger = LoggerFactory.getLogger(TrendsController.class);
//	-------------------- Count of Language ----------------------------------
	
	@GetMapping(path = "/trends/language")
	@ResponseBody
	public List<Map<String, String>> getAlllang() {
		logger.info("Trend Controller Called-- Get Trend of Language");
		List<Map<String,String>> list = null;
		try {
				list = trendDaoImp.getCountLang();
		}catch(NoRecordFound e) {
			logger.error("Trend Controller Called-- No Record Found");
		}
		return list;
	}
//	---------------- Count Skills -----------------------------------
	@GetMapping(path = "/trends/skills")
	@ResponseBody
	public  List<Map<String, String>> getAllskills()  {	
		logger.info("Trend Controller Called-- Get Trend of Skills");
		List<Map<String,String>> list = null;
		try {
			list = trendDaoImp.getCountSkills();
		}catch(NoRecordFound e) {
			logger.error("Trend Controller Called-- No Record Found");
		}
		return list;
	}

//	---------------- Count Demand -----------------------------------
	@GetMapping(path = "/trends/demand")
	@ResponseBody
	public  List<Map<String, String>> getAllDemands() {
		logger.info("Trend Controller Called-- Get Trend of Demand");
		List<Map<String,String>> list = null;
		try {
		 list = trendDaoImp.getCountDemand();
		}catch(NoRecordFound e) {
			logger.error("Trend Controller Called-- No Record Found");
		}
		return list;
	}
//	---------------- Count Min. Exp -----------------------------------
	@GetMapping(path = "/trends/minxp")
	@ResponseBody
	public  List<Map<String, String>> getAllminxp() {
		List<Map<String,String>> list = null;
		try {
			list =  trendDaoImp.getCountMinxp();
		}catch(NoRecordFound e) {
			logger.error("Trend Controller Called-- No Record Found");
		}
		return list;
	}	
	
//	------------------- Total Number of Users ----------------------------
	@GetMapping(path = "/trends/countusers")
	@ResponseBody
	public  List<Map<String, String>> getcountUsers() {	
		logger.info("Trend Controller Called-- Get Flash Cards");
		List<Map<String,String>> list = null;
		try {
			list = trendDaoImp.getFlashCards();
		}catch(NoRecordFound e) {
			logger.error("Trend Controller Called-- No Record Found");
		}
		return list;
	}
	
}
