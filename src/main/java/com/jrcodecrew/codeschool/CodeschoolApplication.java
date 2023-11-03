package com.jrcodecrew.codeschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class CodeschoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeschoolApplication.class, args);
	}

}
