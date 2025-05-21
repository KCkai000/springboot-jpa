package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.dto.UserDto;

@Service
public interface UserService {
	public UserDto getUser(String username);
	public void addUser(String username, String password, String email, Boolean active, String role);
	//可往下自定義
}
