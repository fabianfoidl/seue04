package com.se.ue04.boot;

import com.se.ue04.Constants;
import com.se.ue04.model.Route;
import com.se.ue04.model.Vehicle;
import com.se.ue04.repository.RouteRepository;
import com.se.ue04.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;

// class for importing data into database on startup
@Component
public class DataLoader implements CommandLineRunner {

    private VehicleRepository vehicleRepository;
    private RouteRepository routeRepository;

    @Autowired
    public void setVehicleRepository(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Autowired
    public void setRouteRepository(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // only for initialization

        /*
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setDescription("mit Lederausstattung");
        vehicle1.setDriver("Max Musterfahrer");
        vehicle1.setCarBrand("Mercedes Benz E-Klasse");
        vehicle1.setSeats(4);
        vehicle1.setAvailable(true);

        vehicleRepository.save(vehicle1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setDescription("9 Sitze");
        vehicle2.setDriver("Hildebrant Gollwitzer");
        vehicle2.setCarBrand("VW T5 Kombi");
        vehicle2.setSeats(8);
        vehicle2.setAvailable(true);

        vehicleRepository.save(vehicle2);

        Vehicle vehicle3 = new Vehicle();
        vehicle3.setDescription("Lichtgeschwindigkeit");
        vehicle3.setDriver("Kay Spitz");
        vehicle3.setCarBrand("Audi R8");
        vehicle3.setSeats(1);
        vehicle3.setAvailable(true);

        vehicleRepository.save(vehicle3);

        Vehicle vehicle4 = new Vehicle();
        vehicle4.setDescription("Hybrid");
        vehicle4.setDriver("Erhardt Schirlitz");
        vehicle4.setCarBrand("Toyota Prius+");
        vehicle4.setSeats(4);
        vehicle4.setAvailable(true);

        vehicleRepository.save(vehicle4);

        Vehicle vehicle5 = new Vehicle();
        vehicle5.setDescription("Hybrid");
        vehicle5.setDriver("Sigismund Krieger");
        vehicle5.setCarBrand("Toyota RAV4 Hybrid");
        vehicle5.setSeats(4);
        vehicle5.setAvailable(true);

        vehicleRepository.save(vehicle5);

        Route route1 = new Route();
        route1.setRouteId(Constants.START_HOTEL);
        route1.setDepart("Hotel");
        route1.setArrival("Flughafen");
        route1.setDuration(Duration.ofMinutes(30));

        routeRepository.save(route1);

        Route route2 = new Route();
        route2.setRouteId(Constants.START_AIRPORT);
        route2.setDepart("Flughafen");
        route2.setArrival("Hotel");
        route2.setDuration(Duration.ofMinutes(30));

        routeRepository.save(route2);

        */

    }
}
