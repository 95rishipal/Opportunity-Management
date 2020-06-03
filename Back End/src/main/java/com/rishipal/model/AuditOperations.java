package com.rishipal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AuditOperation")
public class AuditOperations {
//	--------------- Columns ----------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	Date date;
	String userId;
	String userName;
	String operation;
	String oldValues;
	String newValues;
	

}
