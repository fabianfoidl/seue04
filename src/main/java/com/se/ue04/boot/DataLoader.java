package com.se.ue04.boot;

import com.se.ue04.model.Vehicle;
import com.se.ue04.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// class for importing data into database on startup
@Component
public class DataLoader implements CommandLineRunner {

    private VehicleRepository vehicleRepository;

    @Autowired
    public void setVehicleRepository(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setDescription("Testfahrzeug");
        vehicle1.setDriver("Max Musterfahrer");
        vehicle1.setCarBrand("Mercedes");
        vehicle1.setSeats(5);
        vehicle1.setAvailable(true);

        vehicleRepository.save(vehicle1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setDescription("Testfahrzeug2");
        vehicle2.setDriver("Anna Musterfahrerin");
        vehicle2.setCarBrand("Fiat");
        vehicle2.setSeats(5);
        vehicle2.setAvailable(true);

        vehicleRepository.save(vehicle2);

        Vehicle vehicle3 = new Vehicle();
        vehicle3.setDescription("Testfahrzeug3");
        vehicle3.setDriver("Anna Musterfahrerin");
        vehicle3.setCarBrand("BMW");
        vehicle3.setSeats(5);
        vehicle3.setAvailable(true);

        vehicleRepository.save(vehicle3);

    }
}
