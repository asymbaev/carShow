package com.binary.carShow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


//@EnableAutoConfiguration // This enables Spring Boot automatic configuration.Spring Boot will automatically
//configure your project based on the dependencies// For example, if you have the spring-boot-starter web dependency
//@ComponentScan // This enable the Spring Boot component scam to find all of the components in the application
//@Configuration // This annotation is used to define a configuration class that provides bean to the spring application content
@SpringBootApplication
public class CarShowApplication {
private static final Logger logger = LoggerFactory.getLogger(CarShowApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CarShowApplication.class, args);
		logger.info("Application started");
	}

}
