package com.cognizant.hospitalfrontdesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@ConfigurationPropertiesScan("com.cognizant.hospitalfrontdesk.model")
public class HospitalfrontdeskApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalfrontdeskApplication.class, args);
	}

}
