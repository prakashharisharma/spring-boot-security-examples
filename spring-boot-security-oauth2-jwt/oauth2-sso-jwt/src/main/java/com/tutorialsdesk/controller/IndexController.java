package com.tutorialsdesk.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	@GetMapping("/user")
	public String echoTheUsersEmailAddress(Principal principal) {
	   return "Hey there! Your username is: " + principal.getName();
	}
}
