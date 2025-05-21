package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan  //啟用 wEBfILTER 掃描，因為springboot 預設會忽略 JAVAWEB 基本的@
public class SpringbootSsrRoomApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootSsrRoomApplication.class, args);
	}

}
