package com.tutorialsdesk.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	@GetMapping("/")
	public String echoTheUsersEmailAddress(Principal principal) {
	   return "Hey there! Your email address is: " + principal.getName();
	}
}
