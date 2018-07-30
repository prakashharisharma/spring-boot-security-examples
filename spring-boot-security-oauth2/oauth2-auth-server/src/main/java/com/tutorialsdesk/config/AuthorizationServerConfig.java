package com.tutorialsdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	private TokenStore tokenStore = new InMemoryTokenStore();

	  @Autowired
	  private AuthenticationManager authenticationManager;

	  @Override
	  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	      endpoints
	        .authenticationManager(authenticationManager)
	        .tokenStore(tokenStore);
	  }

	  @Override
	  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
	      //security.checkTokenAccess("hasAuthority('USER')");
		  security
			.tokenKeyAccess("permitAll()")
			.checkTokenAccess("isAuthenticated()");
	  }

	  @Override
	  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	      
		  clients
	          .inMemory()
	            .withClient("oauth_client_app")
	            .secret("oauth_client_secret")
	            .authorities("USER")
	            .authorizedGrantTypes("client_credentials", "password","authorization_code","refresh_token")
	            .scopes("read", "write")
	            .accessTokenValiditySeconds(1800)
	           .and() 
	      
	      
	   // Confidential client where client secret can be kept safe (e.g. server side)
          .withClient("confidential").secret("secret")
          .authorizedGrantTypes("client_credentials", "authorization_code", "refresh_token")
          .scopes("read", "write")
          .redirectUris("http://localhost:8080/client/")

          .and()

           // Public client where client secret is vulnerable (e.g. mobile apps, browsers)
          .withClient("public") // No secret!
          .authorizedGrantTypes("implicit")
          .scopes("read")
          .redirectUris("http://localhost:8080/client/")
          .and()

           //Trusted client: similar to confidential client but also allowed to handle user password
          .withClient("trusted").secret("secret")
          .authorities("ROLE_TRUSTED_CLIENT")
          .authorizedGrantTypes("client_credentials", "password", "authorization_code", "refresh_token")
          .scopes("read", "write")
          .redirectUris("http://localhost:8080/client/")
  ;
	  }  
	
}
