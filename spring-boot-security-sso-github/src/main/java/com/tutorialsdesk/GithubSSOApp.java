package com.tutorialsdesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

@SpringBootApplication
@EnableOAuth2Sso
public class GithubSSOApp {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(GithubSSOApp.class, args);
	}

}
