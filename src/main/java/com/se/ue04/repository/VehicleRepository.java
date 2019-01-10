package com.se.ue04.repository;

import com.se.ue04.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {

    // find By bleibt immer gleich; Marke MUSS genau so heissen wie das Feld
    Vehicle findByCarBrand(String marke);
    List<Vehicle> findByCarBrandAndDescription(String carBrand, String description);
    List<Vehicle> findByCarBrandAndDescriptionIn(String carBrand, List<String> description);
    List<Vehicle> findBySeatsGreaterThanEqual(int neededSeats);
}
