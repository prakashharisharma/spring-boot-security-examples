package com.tutorialsdesk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilter(digestAuthenticationFilter()) // register digest entry point
				.exceptionHandling().authenticationEntryPoint(digestEntryPoint()) // on exception ask for digest
																					// authentication
				.and().httpBasic() // it indicate basic authentication is requires
				.and().authorizeRequests().antMatchers("/home").permitAll() // /home will be accessible directly, no
																			// need of any authentication
				.anyRequest().authenticated();
		http.csrf().disable();
	}

	DigestAuthenticationFilter digestAuthenticationFilter() throws Exception {
		DigestAuthenticationFilter digestAuthenticationFilter = new DigestAuthenticationFilter();
		digestAuthenticationFilter.setUserDetailsService(userDetailsServiceBean());
		digestAuthenticationFilter.setAuthenticationEntryPoint(digestEntryPoint());
		return digestAuthenticationFilter;
	}

	@Override
	@Bean
	public UserDetailsService userDetailsServiceBean() {
		InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
		inMemoryUserDetailsManager.createUser(User.withUsername("user1").password("secret1").roles("USER").build());
		inMemoryUserDetailsManager.createUser(User.withUsername("user2").password("secret2").roles("USER").build());
		return inMemoryUserDetailsManager;
	}

	@Bean
	DigestAuthenticationEntryPoint digestEntryPoint() {
		DigestAuthenticationEntryPoint bauth = new DigestAuthenticationEntryPoint();
		bauth.setRealmName("TutorialsDesk Realm");
		bauth.setKey("MySecureKey");
		return bauth;
	}

	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
		return authenticationManager();
	}
}
