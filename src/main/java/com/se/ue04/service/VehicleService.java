package com.se.ue04.service;

import com.se.ue04.model.Vehicle;
import com.se.ue04.repository.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private Logger LOG = LoggerFactory.getLogger(VehicleService.class);

    private VehicleRepository vehicleRepository;

    @Autowired
    public void setVehicleRepository(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle getVehicle(String id) {
        LOG.info("Getting the vehicle with the given id: " + id);
        return vehicleRepository.findById(id).orElse(null);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public List<Vehicle> getAllVehiclesFittedSeatCount(int seats) {
        return vehicleRepository.findBySeatsGreaterThanEqualAndAvailable(seats, true);
    }

    public void saveVehicle(Vehicle vehicle) {
        try {
            LOG.info("Saving vehicle...");
            vehicleRepository.save(vehicle);
        } catch (Exception e) {
            LOG.error("An error occurred during vehicle saving: " + e.getMessage());
        }

    }

    public Vehicle updateVehicle(Vehicle vehicleToUpdate, String id) {
        Vehicle foundVehicle = vehicleRepository.findById(id).orElse(null);
        try {
            foundVehicle.setDescription(vehicleToUpdate.getDescription());
            foundVehicle.setDriver(vehicleToUpdate.getDriver());
            foundVehicle.setCarBrand(vehicleToUpdate.getCarBrand());
            foundVehicle.setSeats(vehicleToUpdate.getSeats());
            foundVehicle.setAvailable(vehicleToUpdate.isAvailable());
            return vehicleRepository.save(foundVehicle);
        } catch (Exception e) {
            LOG.error("An error occurred during update of vehicle " + e.getMessage());
        }
        return vehicleToUpdate;

    }

    public void deleteVehicle(String id) {
        try {
            vehicleRepository.deleteById(id);
        } catch (Exception e) {
            LOG.error("An error occurred during deleting of vehicle " + e.getMessage());
        }

    }
}
