package com.tutorialsdesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@SpringBootApplication
public class Oauth2ResourceJwtApp {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Oauth2ResourceJwtApp.class, args);
	}

}
