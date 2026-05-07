package com.pago_jj.pago_jj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class PagoJjApplication {

	public static void main(String[] args) {
		SpringApplication.run(PagoJjApplication.class, args);
	}

}
