package com.se.ue04.controller;

import com.se.ue04.Constants;
import com.se.ue04.model.BookedDrive;
import com.se.ue04.model.Vehicle;
import com.se.ue04.service.BookedDrivesService;
import com.se.ue04.service.VehicleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/api/availablerides/")
@Api(value = "VehiclesControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class AvailableRidesController {

    private VehicleService vehicleService;
    private BookedDrivesService bookedDrivesService;

    private Logger LOG = LoggerFactory.getLogger(AvailableRidesController.class);

    @Autowired
    public void setBookedDrivesService(BookedDrivesService bookedDrivesService) {
        this.bookedDrivesService = bookedDrivesService;
    }

    @Autowired
    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    @ApiOperation("Gets all available drives")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    public List<Vehicle> getAllVehicles(@RequestParam("date") String dateString, @RequestParam("time") String timeString,
                                        @RequestParam("seats") String seatsString, @RequestParam("route") String route) {
        // route is already in integer format
        int seats = Integer.parseInt(seatsString);
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy HHmmss");
        Date date = null;
        try {
            date = sdf.parse(dateString + " " + timeString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Vehicle> vehiclesWithEnoughSeats = vehicleService.getAllVehiclesFittedSeatCount(seats);
        List<BookedDrive> bookedDrivesWithSameTimeRoute = bookedDrivesService.getAllByRouteAndTime(date, route);

        for (BookedDrive bd : bookedDrivesWithSameTimeRoute) {
            vehiclesWithEnoughSeats.removeIf(v -> (v.getId().equals(bd.getVehicle())));
        }



        LOG.info(bookedDrivesWithSameTimeRoute.toString());

        //return bookedDrivesService.getAllAvailableDrives();
        return vehiclesWithEnoughSeats;
    }


}
