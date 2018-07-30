package com.tutorialsdesk.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.JwtAccessTokenConverterConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
public class JwtConfiguration extends DefaultAccessTokenConverter implements JwtAccessTokenConverterConfigurer {
	@Override
    public void configure(JwtAccessTokenConverter converter) {
           converter.setAccessTokenConverter(this);
    }
}
