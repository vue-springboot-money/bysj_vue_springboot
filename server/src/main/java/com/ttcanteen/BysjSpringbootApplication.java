package com.ttcanteen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class BysjSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BysjSpringbootApplication.class, args);
	}

}
