package com.tutorialsdesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

@EnableOAuth2Sso
@SpringBootApplication
public class OktaSSOApp {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(OktaSSOApp.class, args);
	}

}
