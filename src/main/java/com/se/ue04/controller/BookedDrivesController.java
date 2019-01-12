package com.se.ue04.controller;

import com.se.ue04.model.BookedDrive;
import com.se.ue04.service.BookedDrivesService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping(path = "/api/bookeddrives/")
@Api(value = "/api/bookeddrives", tags = "/bookeddrives", description = "Manage booked rides")
public class BookedDrivesController {

    BookedDrivesService bookedDrivesService;

    @Autowired
    public void setBookedDrivesService(BookedDrivesService bookedDrivesService) {
        this.bookedDrivesService = bookedDrivesService;
    }

    @RequestMapping(path = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a new booking for a ride", notes = "Post to save a new ride.", response = BookedDrive.class, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = BookedDrive.class), @ApiResponse(code = 400, message = "Error in request")})
    public BookedDrive saveBookedDrive(@ApiParam(value = "BookedDrive model in JSON format. ID must not be provided, noGuest = integer, route = number of route, time = java date object, user = userId, vehicle = vehicleId. Attention: no validation of values!", required = true) @RequestBody BookedDrive bookedDriveToSave) {
        return bookedDrivesService.saveBookedDrive(bookedDriveToSave);
    }

    @RequestMapping(path = "{user}", method = RequestMethod.GET)
    @ApiOperation(value = "Get booked drives by user", notes = "Get all booked rides from a user. Also past rides are responded.", response = BookedDrive.class, responseContainer = "List", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = BookedDrive.class), @ApiResponse(code = 400, message = "Error in request")})
    public List<BookedDrive> getBookedDriveByUser(@ApiParam(value = "Id (email) of user", required = true) @PathVariable(name = "user") String user) {
        return bookedDrivesService.getBookedDriveByUser(user);
    }

    @RequestMapping(path = "{ride}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete booked ride", notes = "Deletes the ride with given id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Error in request")})
    public void deleteBookedDrive(@ApiParam(value = "Id of booked ride", required = true) @PathVariable(name = "ride") String rideId) {
        bookedDrivesService.deleteBookedDrive(rideId);
    }

}
