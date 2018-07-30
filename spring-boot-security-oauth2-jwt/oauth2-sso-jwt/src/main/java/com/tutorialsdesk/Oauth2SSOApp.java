package com.tutorialsdesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextListener;

@SpringBootApplication
public class Oauth2SSOApp {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Oauth2SSOApp.class, args);
	}
	
/*	@Bean
	public FilterRegistrationBean oauth2ClientFilterRegistration(
	    OAuth2ClientContextFilter filter) {
	  FilterRegistrationBean registration = new FilterRegistrationBean();
	  registration.setFilter(filter);
	  registration.setOrder(-100);
	  return registration;
	}*/

	
	@Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }
}
