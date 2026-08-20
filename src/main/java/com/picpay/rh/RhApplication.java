package com.picpay.rh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RhApplication {

	public static void main(String[] args) {
		SpringApplication.run(RhApplication.class, args);
	}

}
