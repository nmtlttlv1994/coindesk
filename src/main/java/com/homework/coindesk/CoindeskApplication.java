package com.homework.coindesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@Configuration
@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.homework.coindesk.config",
		"com.homework.coindesk.controller",
		"com.homework.coindesk.dao",
		"com.homework.coindesk.dto",
		"com.homework.coindesk.entity",
		"com.homework.coindesk.filter",
		"com.homework.coindesk.mapper",
		"com.homework.coindesk.repository",
		"com.homework.coindesk.response",
		"com.homework.coindesk.service",
		"com.homework.coindesk.util"})
public class CoindeskApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoindeskApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
