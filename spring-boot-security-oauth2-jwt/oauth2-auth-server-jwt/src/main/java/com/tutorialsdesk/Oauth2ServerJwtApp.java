package com.tutorialsdesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Oauth2ServerJwtApp {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Oauth2ServerJwtApp.class, args);
	}

}

//http://localhost:8081/oauth/token?grant_type=client_credentials&client_id=oauth_client_app&client_secret=oauth_client_secret

//http://localhost:8081/oauth/authorize?scope=read&response_type=code&client_id=oauth_client_app&redirect_uri=http://google.com

//http://localhost:8081/uaa/oauth/token?grant_type=authorization_code&code=nv7sfR&client_id=oauth_client_app&client_secret=oauth_client_secret&redirect_uri=http://google.com

//http://localhost:8081/oauth/authorize?grant_type=authorization_code&client_id=oauth_client_app&client_secret=oauth_client_secret&redirect_uri=http://google.com

//https://localhost:9443/oauth2/token?grant_type=client_credentials&client_id=oauth_client_app&client_secret=oauth_client_secret

