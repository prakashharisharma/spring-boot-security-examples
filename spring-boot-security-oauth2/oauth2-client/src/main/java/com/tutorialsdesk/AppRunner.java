package com.tutorialsdesk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Component;

//@Component
public class AppRunner implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);
	
	@Autowired
    private OAuth2RestOperations restTemplate;
	
	@Value("${config.oauth2.resourceURI}")
    private String resourceURI;
	
	@Override
	public void run(String... args) throws Exception {
		
		String result = restTemplate.getForObject(resourceURI, String.class);
    	
    	logger.info(result);
	}

}
