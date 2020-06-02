package com.rishipal.main;

import java.util.Arrays;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.rishipal.resource.*;
import com.rishipal.model.*;


@SpringBootApplication
@ComponentScan(basePackages = {"com.rishipal.resource","com.rishipal.jdbc"})
@EntityScan("com.rishipal.model")
@EnableJpaRepositories("com.rishipal.repo")
public class OpportunityManagmenetApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext  context = SpringApplication.run(OpportunityManagmenetApplication.class, args);
		 System.out.println("Test  "+context.containsBeanDefinition("test"));
		 System.out.println("Opportunity  "+context.containsBeanDefinition("oppo"));
		 System.out.println("Skill  "+context.containsBeanDefinition("skill"));
		 System.out.println("User  "+context.containsBeanDefinition("user"));
	}

}
