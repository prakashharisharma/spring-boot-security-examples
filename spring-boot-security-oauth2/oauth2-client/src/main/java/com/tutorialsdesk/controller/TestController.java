package com.tutorialsdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Autowired
    private OAuth2RestOperations restTemplate;

    @Value("${config.oauth2.resourceURI}")
    private String resourceURI;

    @RequestMapping("/hi")
    public String home() {
    	
    	String result = restTemplate.getForObject(resourceURI, String.class);
    	
    	System.out.println(result);
    	
        return "hi";
    }
}
