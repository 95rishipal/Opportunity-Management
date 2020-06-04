package com.opportunitymanagment.accolite.main;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.opportunitymanagment.accolite.models.*;
import com.opportunitymanagment.accolite.resources.*;


@SpringBootApplication
@ComponentScan(basePackages = {"com.opportunitymanagment.accolite.resources","com.opportunitymanagment.accolite.jdbc"})
@EntityScan("com.opportunitymanagment.accolite.models")
@EnableJpaRepositories("com.opportunitymanagment.accolite.repo")
public class OpportunityManagmenetApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext  context = SpringApplication.run(OpportunityManagmenetApplication.class, args);
		 System.out.println("Test  "+context.containsBeanDefinition("test"));
		 System.out.println("Opportunity  "+context.containsBeanDefinition("oppo"));
		 System.out.println("Skill  "+context.containsBeanDefinition("skill"));
		 System.out.println("User  "+context.containsBeanDefinition("user"));
	}
}
