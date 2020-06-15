package com.oppo.accolite;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@SpringBootApplication
public class OpportunityManagmenetApplication {
	  private static final Logger logger = LoggerFactory.getLogger(OpportunityManagmenetApplication.class);
      public static void main(String[] args) {
            SpringApplication.run(OpportunityManagmenetApplication.class, args);
            System.out.println("**************Server Running****************");
            logger.info("Server is Running Fine!!!");
            
      }
      
    
      @Bean
  	public WebMvcConfigurer corsConfigurer() {
  		return new WebMvcConfigurer() {
  			@Override
  			public void addCorsMappings(CorsRegistry registry) {
  				registry.addMapping("/**").allowedOrigins("http://localhost:4200");
  			}
  		};
  	}
}