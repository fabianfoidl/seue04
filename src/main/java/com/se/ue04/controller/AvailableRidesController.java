package com.se.ue04.controller;

import com.se.ue04.Constants;
import com.se.ue04.model.BookedDrive;
import com.se.ue04.model.Vehicle;
import com.se.ue04.service.BookedDrivesService;
import com.se.ue04.service.VehicleService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path = "/api/availablerides/")
@Api(value = "/api/availablerides", tags = "/availablerides", description = "Get available rides")
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
    @ApiOperation(value = "Get all available rides", notes = "The value date, time, needed seats and route have to be provided. As Response, all available vehicles will be provided.",
            response = Vehicle.class, responseContainer = "List", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Error in request")})
    public List<Vehicle> getAllVehicles(@ApiParam(value = "Param for requested date. Must be in format ddMMyyyy.", required = true) @RequestParam("date") String dateString,
                                        @ApiParam(value = "Param for requested time. Must be in format hhmmss.", required = true) @RequestParam("time") String timeString,
                                        @ApiParam(value = "Param for the number of seats needed. Must be an integer." , required = true) @RequestParam("seats") String seatsString,
                                        @ApiParam(value = "Param for the route. Must be an integer (either 1 for hotel to airport or 2 for airport to hotel).", required = true) @RequestParam("route") String route) {
        // route is already in integer format
        int seats = Integer.parseInt(seatsString);
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy HHmmss");
        Date date = null;
        try {
            date = sdf.parse(dateString + " " + timeString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // get all vehicles which have enough seats
        List<Vehicle> vehiclesWithEnoughSeatsAndAvailable = vehicleService.getAllVehiclesFittedSeatCount(seats);
        List<Vehicle> availableRides = vehiclesWithEnoughSeatsAndAvailable;

        // remove all vehicles which start or end within the same location 59 minutes before or after
        Date oneHourEarlier = new Date(date.getTime() - TimeUnit.MINUTES.toMillis(59));
        Date oneHourLater = new Date(date.getTime() + TimeUnit.MINUTES.toMillis(59));

        List<BookedDrive> bookedDrivesOneHourLaterAndEarlier = bookedDrivesService.getAllByRouteAndDateBetween(route, oneHourEarlier, oneHourLater);

        for (BookedDrive bd : bookedDrivesOneHourLaterAndEarlier) {
            availableRides.removeIf(v -> (v.getId().equals(bd.getVehicle())));
        }

        // remove all vehicles which start or end within the other location 29 minutes before or after
        Date halfHourEarlierOtherLocation = new Date(date.getTime() - TimeUnit.MINUTES.toMillis(29));
        Date halfHourLaterOtherLocation = new Date(date.getTime() + TimeUnit.MINUTES.toMillis(29));

        // get the route no of other location
        String otherRoute = (Constants.START_HOTEL.equals(route) ? Constants.START_AIRPORT : Constants.START_HOTEL);

        List<BookedDrive> bookedDrivesHalfHourLaterAndEarlierOtherLoc = bookedDrivesService.getAllByRouteAndDateBetween(otherRoute, halfHourEarlierOtherLocation, halfHourLaterOtherLocation);

        for (BookedDrive bd : bookedDrivesHalfHourLaterAndEarlierOtherLoc) {
            availableRides.removeIf(v -> (v.getId().equals(bd.getVehicle())));
        }

        return availableRides;
    }

}
