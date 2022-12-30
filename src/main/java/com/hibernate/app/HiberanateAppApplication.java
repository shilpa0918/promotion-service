package com.hibernate.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class HiberanateAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(HiberanateAppApplication.class, args);
	}

}
