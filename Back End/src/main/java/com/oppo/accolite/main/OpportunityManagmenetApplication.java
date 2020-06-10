package com.oppo.accolite.main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = {"com.opportunitymanagement.accolite.interceptor","com.opportunitymanagment.accolite.controller","com.opportunitymanagement.accolite.dao"})

public class OpportunityManagmenetApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpportunityManagmenetApplication.class, args);
		System.out.println("**************Server Running****************");
	}
}
