package com.se.ue04;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Ue04Application extends SpringBootServletInitializer implements CommandLineRunner {



	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Ue04Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Ue04Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
