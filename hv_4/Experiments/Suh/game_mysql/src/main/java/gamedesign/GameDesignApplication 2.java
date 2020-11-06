package com.example.gamedesign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.*")
//@EnableJpaRepositories("com.example.register*")
@EntityScan("com.example.model")  

public class GameDesignApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameDesignApplication.class, args);
	}
}
