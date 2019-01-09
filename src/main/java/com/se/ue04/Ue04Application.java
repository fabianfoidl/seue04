package com.se.ue04;

import com.se.ue04.repository.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Ue04Application implements CommandLineRunner {

	private VehicleRepository vehicleRepository;

	private Logger LOG = LoggerFactory.getLogger(Ue04Application.class);

	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Autowired
	public void setVehicleRepository(VehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(Ue04Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {




	}
}
