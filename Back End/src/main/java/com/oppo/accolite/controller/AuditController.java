package com.oppo.accolite.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oppo.accolite.Exception.NoRecordFound;
import com.oppo.accolite.dao.AuditDaoImp;
import com.oppo.accolite.models.*;

@Controller
public class AuditController {
	  
	
      @Autowired
      AuditDaoImp auditDaoImp;
      
      private static final Logger logger = LoggerFactory.getLogger(AuditController.class);
      
      @GetMapping(path = "/audit/getall")
      @ResponseBody
      public List<Audit> getAllAudit() {
    	  	logger.info("Audit Controller Called-- GetAll");
    	  	List<Audit> list = null;
    	  	try {
    	  		list = auditDaoImp.getAllAudit();
    	  	}catch(NoRecordFound e) {
    	  		logger.error("Audit Controller Called-- No Record Found");
    	  	}
    	  	return list;
      }

      @GetMapping(path = "/audit/search/{col}/{place}", produces = {MediaType.APPLICATION_JSON_VALUE})
      @ResponseBody
      public List<Audit> searchBy(@PathVariable("col") String col, @PathVariable("place") String place) {
    	  	logger.info("Audit Controller Called-- Search");
    	  	List<Audit> list = null;
    	  	try {
    	  		list = auditDaoImp.search(col, place);
    	  	}catch(NoRecordFound e) {
    	  		logger.error("Audit Controller Called-- No Record Found");
    	  	}
    	  	return list;
      }

}