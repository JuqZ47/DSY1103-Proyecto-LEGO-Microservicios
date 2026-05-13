package com.auth_jj.auth_jj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AuthJjApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthJjApplication.class, args);
	}

}
