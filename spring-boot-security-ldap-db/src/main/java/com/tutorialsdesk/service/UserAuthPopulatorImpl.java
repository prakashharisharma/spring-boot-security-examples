package com.tutorialsdesk.service;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.stereotype.Service;

import com.tutorialsdesk.model.Role;
import com.tutorialsdesk.repo.UserRepository;

@Service("dbRoleService")
public class UserAuthPopulatorImpl implements LdapAuthoritiesPopulator {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations userData,
			String username) {
		Collection<GrantedAuthority> gas = new HashSet<GrantedAuthority>();
		com.tutorialsdesk.model.User user = null;
		try {
			user = userRepository.findByUsername(username);
			
			System.out.println("User found in DB " + user);
			
		} catch (Exception e) {
			System.out.println("User Not Found");
			e.printStackTrace();
		}

		System.out.println("Mapping users roles from DB.....");
		
		if (user != null) {
			for (Role role : user.getUserRoles()) {
				if (role.getRole().equalsIgnoreCase("ROLE_ADMIN")) {
					gas.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
				} else if (role.getRole().equalsIgnoreCase("ROLE_USER")) {
					gas.add(new SimpleGrantedAuthority("ROLE_USER"));
				} else if (role.getRole().equalsIgnoreCase("ROLE_API")) {
					gas.add(new SimpleGrantedAuthority("ROLE_API"));
				} else {
					gas.add(new SimpleGrantedAuthority("ROLE_NA"));
				}
			}
		}
		return gas;
	}

}
