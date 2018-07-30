package com.tutorialsdesk.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	//private TokenStore tokenStore = new InMemoryTokenStore();

	private static final Logger log = LoggerFactory.getLogger(AuthorizationServerConfig.class);

	@Value("${config.oauth2.privateKey}")
	private String privateKey;

	@Value("${config.oauth2.publicKey}")
	private String publicKey;

	@Autowired
	private AuthenticationManager authenticationManager;

	 @Bean
	    public JwtAccessTokenConverter tokenEnhancer() {
	        log.info("Initializing JWT with public key:\n" + publicKey);
	        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	        converter.setSigningKey(privateKey);
	        converter.setVerifierKey(publicKey);
	        return converter;
	    }

	    @Bean
	    public JwtTokenStore tokenStore() {
	        return new JwtTokenStore(tokenEnhancer());
	    }
	
	    /**
	     * Defines the security constraints on the token endpoints /oauth/token_key and /oauth/check_token
	     * Client credentials are required to access the endpoints
	     *
	     * @param oauthServer
	     * @throws Exception
	     */
		@Override
		public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
			security
            .tokenKeyAccess("isAnonymous() || hasRole('ROLE_TRUSTED_CLIENT')") // permitAll()
            .checkTokenAccess("hasRole('TRUSTED_CLIENT')"); // isAuthenticated()
		}
	    /**
	     * Defines the authorization and token endpoints and the token services
	     *
	     * @param endpoints
	     * @throws Exception
	     */ 
	    @Override
	    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	        endpoints

	                // Which authenticationManager should be used for the password grant
	                // If not provided, ResourceOwnerPasswordTokenGranter is not configured
	                .authenticationManager(authenticationManager)

	                 //Use JwtTokenStore and our jwtAccessTokenConverter
	                .tokenStore(tokenStore())
	                .accessTokenConverter(tokenEnhancer())
	                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
	    }



	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		clients.inMemory().withClient("oauth_client_app").secret("oauth_client_secret").authorities("ROLE_TRUSTED_CLIENT","USER","ROLE_USER")
				.authorizedGrantTypes("client_credentials", "password", "authorization_code", "refresh_token")
				.scopes("read", "write").accessTokenValiditySeconds(1800).autoApprove(true)
				.and()

				// Confidential client where client secret can be kept safe (e.g. server side)
				.withClient("confidential").secret("secret")
				.authorizedGrantTypes("client_credentials", "authorization_code", "refresh_token")
				.scopes("read", "write").redirectUris("http://localhost:8080/client/")

				.and()

				// Public client where client secret is vulnerable (e.g. mobile apps, browsers)
				.withClient("public") // No secret!
				.authorizedGrantTypes("implicit").scopes("read").redirectUris("http://localhost:8080/client/").and()

				// Trusted client: similar to confidential client but also allowed to handle
				// user password
				.withClient("trusted").secret("secret").authorities("ROLE_TRUSTED_CLIENT")
				.authorizedGrantTypes("client_credentials", "password", "authorization_code", "refresh_token")
				.scopes("read", "write").redirectUris("http://localhost:8080/client/");
	}

}
