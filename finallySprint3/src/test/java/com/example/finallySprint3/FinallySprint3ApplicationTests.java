package com.example.finallySprint3;

import com.example.finallySprint3.model.entriy.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.io.PipedReader;

@SpringBootTest(classes = {FinallySprint3Application.class})
class FinallySprint3ApplicationTests {

	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void testRestTemplate(){
		String result = restTemplate.getForObject("http://localhost:8080/users", String.class);
		System.out.println(result);
	}
}
