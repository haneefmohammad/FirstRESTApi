package com.capg.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.capg.rest.service.ProductService;

@SpringBootApplication
public class FirstRestAppApplication {

	public static void main(String[] args) {
		 SpringApplication.run(FirstRestAppApplication.class, args);
	
	}
	
	@Bean
	RestTemplate restTemplate()
	{
		return new RestTemplate();
	}

}
