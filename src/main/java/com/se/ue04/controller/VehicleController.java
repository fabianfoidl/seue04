package com.se.ue04.controller;

import com.se.ue04.model.Vehicle;
import com.se.ue04.service.VehicleService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;


@RestController
@RequestMapping(path = "/api/vehicles/")
@Api(value = "/api/vehicles", tags = "/vehicles", description = "Manage vehicles")
public class VehicleController {

    private VehicleService vehicleService;

    @Autowired
    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    private Logger LOG = LoggerFactory.getLogger(VehicleController.class);

    @RequestMapping(path = "/", method = RequestMethod.GET)
    @ApiOperation(value = "Gets all vehicles", notes = "Returns also not available vehicles.", response = Vehicle.class, responseContainer = "List", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Vehicle.class, responseContainer = "List"), @ApiResponse(code = 400, message = "Error in request")})
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Gets a vehicle with specific id", notes = "Return vehicle with provided id", response = Vehicle.class, responseContainer = "List", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Vehicle.class, responseContainer = "List"), @ApiResponse(code = 400, message = "Error in request")})
    public Vehicle getVehicle(@ApiParam(value = "The id of the vehicle.", required = true) @PathVariable(name = "id") String id) {
        return vehicleService.getVehicle(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Creates a new vehicle", notes = "Simply post a vehicle object (as JSON) to this URL. Seats should be of type integer, available of type boolean.", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Vehicle.class, responseContainer = "List"), @ApiResponse(code = 400, message = "Error in request")})
    public void saveVehicle(@ApiParam(value = "The vehicle object (as JSON)", required = true) @RequestBody Vehicle vehicleToSave) {
        vehicleService.saveVehicle(vehicleToSave);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Updates an existing vehicle", notes = "Simply put a vehicle object with the id of the vehicle to update an existing vehicle.  Seats should be of type integer, available of type boolean.", response = Vehicle.class, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Vehicle.class, responseContainer = "List"), @ApiResponse(code = 400, message = "Error in request")})
    public Vehicle updateVehicle(@ApiParam(value = "The vehicle object (as JSON)", required = true) @RequestBody Vehicle vehicleToUpdate, @PathVariable(name = "id") String id) {
        return vehicleService.updateVehicle(vehicleToUpdate, id);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Deletes an existing vehicle", notes = "Simply deletes the vehicle of the given id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Vehicle.class, responseContainer = "List"), @ApiResponse(code = 400, message = "Error in request")})
    public void deleteVehicle(@ApiParam(value = "Id of the vehicle which should be deleted", required = true) @PathVariable(name = "id") String id) {
        if (vehicleService.getVehicle(id) == null) {
            throw new NullPointerException();
        }
        vehicleService.deleteVehicle(id);
    }

}
