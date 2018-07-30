package com.tutorialsdesk.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@PreAuthorize("hasRole('USER')")
public class UserController {

	@RequestMapping("/user")
	@PreAuthorize("hasRole('USER')")
	//@PreAuthorize("hasAuthority('ROLE_USER')")
    public Principal resource(Principal principal) {
        return principal;
    }
	
	@RequestMapping("/user1")
	@PreAuthorize("hasRole('ROLE_USER')")
	//@PreAuthorize("hasAuthority('ROLE_USER')")
    public Principal resource1(Principal principal) {
        return principal;
    }
	
	@RequestMapping("/user2")
	@PreAuthorize("hasAuthority('USER')")
    public Principal resource2(Principal principal) {
        return principal;
    }
	
	@RequestMapping("/user3")
	@PreAuthorize("hasAuthority('ROLE_USER')")
    public Principal resource3(Principal principal) {
        return principal;
    }
	
	
	
}

