package com.example.demo;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.service.UserService;

@SpringBootTest
public class UserJPAAddTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testUserAdd() {
		userService.addUser("Happy", "0000", "mini@gmail.com", true, "user");
		System.out.println("User add ok!");
	}
	
}