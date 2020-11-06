package com.ccd.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;


/**
 * All the scans to avoid possible errors
 * 
 * @author jsuh_mac
 *
 */
@SpringBootApplication
@ComponentScan({"com.ccd.app"})
@EntityScan("com.ccd.app.model")  
@EnableJpaRepositories("com.ccd.app")


public class RegisterApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(RegisterApplication.class, args);
	}
}
