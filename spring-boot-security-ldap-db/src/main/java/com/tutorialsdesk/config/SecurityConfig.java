package com.tutorialsdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	 @Qualifier("dbRoleService")
	 LdapAuthoritiesPopulator ldapAuthoritiesPopulator;
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.ldapAuthentication().userDnPatterns("uid={0},ou=people").groupSearchBase("ou=groups").contextSource()
				.url("ldap://localhost:8389/dc=tutorialsdesk,dc=com")
				.and().passwordCompare()
				.passwordEncoder(new LdapShaPasswordEncoder()).passwordAttribute("userPassword").and()
				.ldapAuthoritiesPopulator(ldapAuthoritiesPopulator);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().fullyAuthenticated().and().formLogin();
	}
}
