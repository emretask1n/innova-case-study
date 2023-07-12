package com.emretaskin.innova;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class InnovaApplication {

	public static void main(String[] args) {
		SpringApplication.run(InnovaApplication.class, args);
	}

}
