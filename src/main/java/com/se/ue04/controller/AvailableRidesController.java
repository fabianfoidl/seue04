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
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    public List<Vehicle> getAllVehicles(@RequestParam("datevalue") String dateString, @RequestParam("timevalue") String timeString,
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

        // get all vehicles which have enough seats
        List<Vehicle> vehiclesWithEnoughSeats = vehicleService.getAllVehiclesFittedSeatCount(seats);
        List<Vehicle> availableRides = vehiclesWithEnoughSeats;

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
