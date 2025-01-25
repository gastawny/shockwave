package com.gastawny.shockwave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ShockwaveApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShockwaveApplication.class, args);
	}

}
