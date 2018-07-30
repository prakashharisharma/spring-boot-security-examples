package com.tutorialsdesk.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

//@Configuration
//@EnableResourceServer
public class ResourceServerSecurityConfig extends ResourceServerConfigurerAdapter {

	private static final Logger log = LoggerFactory.getLogger(ResourceServerSecurityConfig.class);

	@Autowired
	TokenStore tokenStore;

	@Autowired
	JwtAccessTokenConverter tokenConverter;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
    	.authorizeRequests().anyRequest().authenticated()
    	.and()
    	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    ;
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		log.info("Configuring ResourceServerSecurityConfigurer ");
		resources.resourceId("foo").tokenStore(tokenStore);
	}

}
