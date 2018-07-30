package com.tutorialsdesk;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"test"})
//@RunWith(SpringRunner.class)
@SpringBootTest
public class Oauth2SSOAppTests {

	@Test
    public void contextLoads() {
		
    }
}
