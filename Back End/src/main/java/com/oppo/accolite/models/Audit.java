package com.oppo.accolite.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;


import org.springframework.jdbc.core.RowMapper;


public class Audit {
	
	//	--------------- Columns ----------------------------

	int id;
	String date;
	int userId;
	String userName;
	String operation;
	String oldValues;
	String newValues;
	String type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getOldValues() {
		return oldValues;
	}
	public void setOldValues(String oldValues) {
		this.oldValues = oldValues;
	}
	public String getNewValues() {
		return newValues;
	}
	public void setNewValues(String newValues) {
		this.newValues = newValues;
	}
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Audit(int id, String date, String oldValues,
			String newValues,String operation, String type, int userId,String userName) {
		super();
		this.id = id;
		Calendar calobj = Calendar.getInstance();
		this.date =  new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime());
		this.userId = userId;
		this.userName = userName;
		this.operation = operation;
		this.oldValues = oldValues;
		this.newValues = newValues;
		this.type=type;
	}
	public Audit(String operation,String type, String oldValues,
			String newValues) {
		super();
		Calendar calobj = Calendar.getInstance();
		this.date =  new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime());
		this.type=type;
		this.operation = operation;
		this.oldValues = oldValues;
		this.newValues = newValues;
	}
	
	public Audit() {}

}
