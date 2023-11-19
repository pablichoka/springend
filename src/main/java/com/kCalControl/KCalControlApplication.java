package com.kCalControl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KCalControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(KCalControlApplication.class, args);
	}

}
