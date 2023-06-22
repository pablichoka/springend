package com.kCalControl;

import com.kCalControl.model.Role;
import com.kCalControl.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.kCalControl.repository")
public class KCalControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(KCalControlApplication.class, args);
	}

}
