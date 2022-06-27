package com.spring.shelf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication()
@Configuration
public class ShelfApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShelfApplication.class, args);
	}

}
