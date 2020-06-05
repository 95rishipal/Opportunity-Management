package com.opportunitymanagment.accolite.main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
@ComponentScan(basePackages = {"com.opportunitymanagment.accolite.JDBCTemplate"})
@EntityScan("com.opportunitymanagment.accolite.models")
public class OpportunityManagmenetApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext  context = SpringApplication.run(OpportunityManagmenetApplication.class, args);
		System.out.println("Working");
	}
}
