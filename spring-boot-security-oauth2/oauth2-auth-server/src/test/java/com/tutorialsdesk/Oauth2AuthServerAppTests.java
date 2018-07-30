package com.tutorialsdesk;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Base64Utils;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class Oauth2AuthServerAppTests {

    @Autowired
    WebApplicationContext context;
    @Autowired
    FilterChainProxy springSecurityFilterChain;
    MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(springSecurityFilterChain).build();
    }

    @Test
    public void shouldHavePermission() throws Exception {
       
    	String token = getAccessToken("user1", "secret1");
    	System.out.println(token);
    	
    	assertThat(!token.isEmpty());
    	
    /*	mvc.perform(get("/user")
                .header("Authorization", "Bearer " + getAccessToken("user1", "secret1"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());*/

    }

    private String getAccessToken(String username, String password) throws Exception {    
        MockHttpServletResponse response = mvc
                .perform(post("/oauth/token")
                        .header("Authorization", "Basic "
                                + new String(Base64Utils.encode(("oauth_client_app:oauth_client_secret")
                                .getBytes())))
                        .param("username", username)
                        .param("password", password)
                        .param("grant_type", "password"))
                .andReturn().getResponse();

        return new ObjectMapper()
                .readValue(response.getContentAsByteArray(), OAuthToken.class)
                .accessToken;
    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class OAuthToken {
        @JsonProperty("access_token")
        public String accessToken;
    }
}