package com.tutorialsdesk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
		auth.userDetailsService(userDetailsServiceBean());

    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.requestMatchers()
        .antMatchers("/login", "/oauth/authorize")
        .and()
        .authorizeRequests()
        .anyRequest().authenticated()
        .and()
       // .httpBasic().and().csrf().disable();
        .formLogin().permitAll().and().csrf().disable();
       // http.httpBasic();
        //http.csrf().disable();
    	
        /*http.authorizeRequests().anyRequest().authenticated();
        http.httpBasic();
        http.csrf().disable();*/
    }
    

	@Override
	@Bean
	public UserDetailsService userDetailsServiceBean() {
		InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
		inMemoryUserDetailsManager.createUser(User.withUsername("user1").password("secret1").roles("USER").build());
		inMemoryUserDetailsManager.createUser(User.withUsername("user2").password("secret2").roles("ADMIN").build());
		inMemoryUserDetailsManager.createUser(User.withUsername("user3").password("secret3").roles("CLIENT").build());
		return inMemoryUserDetailsManager;
	}
}
