package com.opportunitymanagment.accolite.jdbc.test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.opportunitymanagment.accolite.JDBCTemplate.OpportunityJDBCTemplate;
import com.opportunitymanagment.accolite.JDBCTemplate.Trends;

import junit.framework.Assert;

public class TrendJDBCTest {
	
	
	@Test
	public void getAll() {
		 Trends obj = new Trends();
		 ResponseEntity responseEntity = obj.getAllDemands("95rishipal@gmail.com");
		 Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
		 responseEntity = obj.getAlllang("95rishipal@gmail.com");
		 Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
		 responseEntity = obj.getAllminxp("95rishipal@gmail.com");
		 Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
		 responseEntity = obj.getAllskills("95rishipal@gmail.com");
		 Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
		 responseEntity = obj.getcountUsers("95rishipal@gmail.com");
		 Assert.assertEquals(responseEntity.getStatusCodeValue(), 200);
	}
}
