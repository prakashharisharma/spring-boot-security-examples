package com.tutorialsdesk.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorialsdesk.model.Student;


@RestController
@RequestMapping("/students")
public class StudentController {

	@GetMapping("/{rollNumber}")
	public Student getStudent(@PathVariable String rollNumber, @AuthenticationPrincipal final UserDetails user) {

		System.out.println(user.getUsername());

		return new Student("1234", "TestStudent");

	}

}
