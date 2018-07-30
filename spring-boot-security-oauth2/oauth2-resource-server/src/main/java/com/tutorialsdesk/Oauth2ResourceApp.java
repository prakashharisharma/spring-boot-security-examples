package com.tutorialsdesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class Oauth2ResourceApp {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Oauth2ResourceApp.class, args);
	}

}
