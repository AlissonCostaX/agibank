package br.com.alissoncostax.agibank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AgibankApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(AgibankApplication.class, args);
	}

}
