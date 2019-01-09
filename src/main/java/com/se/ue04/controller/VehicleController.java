package com.se.ue04.controller;

import com.se.ue04.model.Vehicle;
import com.se.ue04.service.VehicleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/vehicles/")
@Api(value = "VehiclesControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class VehicleController {

    private VehicleService vehicleService;

    @Autowired
    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    private Logger LOG = LoggerFactory.getLogger(VehicleController.class);

    @RequestMapping(path = "/", method = RequestMethod.GET)
    @ApiOperation("Gets all vehicles")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    @ApiOperation("Gets the vehicle with specific id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Vehicle.class)})
    public Vehicle getVehicle(@PathVariable(name = "id") String id) {
        return vehicleService.getVehicle(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Vehicle saveVehicle(@RequestBody Vehicle vehicleToSave) {
        return vehicleService.saveVehicle(vehicleToSave);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Vehicle updateVehicle(@RequestBody Vehicle vehicleToUpdate, @PathVariable(name = "id") String id) {
        return vehicleService.updateVehicle(vehicleToUpdate, id);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void deleteVehicle(@PathVariable(name = "id") String id) {
        vehicleService.deleteVehicle(id);
    }

}
