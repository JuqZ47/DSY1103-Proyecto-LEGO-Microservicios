package com.inventario_jj.inventario_jj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class InventarioJjApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventarioJjApplication.class, args);
	}

}
