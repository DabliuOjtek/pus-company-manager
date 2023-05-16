package com.pus.companymanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class CompanymanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanymanagerApplication.class, args);
	}

}
