package com.opportunitymanagment.accolite.JDBCTemplate;

import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class Dataservice {
	
	DataSource ds;
	Dataservice(String name){
		switch(name) {
		case "mysql":
			this.ds = this.mysqlDataSource(); 
			break;
		case "h2":
			this.ds = this.h2DataSource();
			break;
		}
	}
	
	public DataSource getDataSource() {
		return ds;
	}
	
	public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/accolitedb");
        dataSource.setUsername("root");
        dataSource.setPassword("12345");
        return dataSource;
    }
	
	
	public DataSource h2DataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testdb");
        dataSource.setUsername("sa");
        dataSource.setPassword("password");
        return dataSource;
	}
	
	
	
}
