package com.tutorialsdesk;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.tutorialsdesk.controller.StudentController;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecurityBasicAppTests {

	@Autowired
	private StudentController studentController;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void contextLoads() {
		assertThat(studentController).isNotNull();
	}

	@Test
    public void home() throws Exception {
		
        String body = this.restTemplate.withBasicAuth("user1", "secret1").getForObject("/students/1234", String.class);
        
        assertThat(body).contains("TestStudent");
        
    }
}
