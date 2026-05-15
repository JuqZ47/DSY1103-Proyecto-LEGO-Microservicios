package com.loyalty_jj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LegoMsLoyaltyJjApplication {

	public static void main(String[] args) {
		SpringApplication.run(LegoMsLoyaltyJjApplication.class, args);
	}
}
