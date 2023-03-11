package com.socialserver.socialserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class SocialserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialserverApplication.class, args);
	}

}
