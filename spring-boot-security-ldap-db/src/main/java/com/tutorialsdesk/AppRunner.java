package com.tutorialsdesk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tutorialsdesk.model.Role;
import com.tutorialsdesk.model.User;
import com.tutorialsdesk.repo.RoleRepository;
import com.tutorialsdesk.repo.UserRepository;

@Component
public class AppRunner implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		roleRepository.deleteAll();
		userRepository.deleteAll();
		
		User user1 = new User("ben","benspassword");
		user1 = userRepository.save(user1);
		
		Role userRole1 = new Role(user1,"USER");
		userRole1 = roleRepository.save(userRole1);
		
		User user2 = new User("prakash","1234");
		user2 = userRepository.save(user2);
		
		Role userRole2 = new Role(user2,"ADMIN");
		userRole2 = roleRepository.save(userRole2);
		
	}

}
