package com.meli.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MeliChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeliChallengeApplication.class, args);
	}

}
