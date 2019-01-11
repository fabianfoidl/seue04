package com.se.ue04.controller;

import com.se.ue04.model.BookedDrive;
import com.se.ue04.model.Vehicle;
import com.se.ue04.service.BookedDrivesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/bookeddrives/")
@Api(value = "BookedDrivesControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookedDrivesController {

    BookedDrivesService bookedDrivesService;

    @Autowired
    public void setBookedDrivesService(BookedDrivesService bookedDrivesService) {
        this.bookedDrivesService = bookedDrivesService;
    }

    @RequestMapping(path = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Create a new booking for a ride")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    public BookedDrive saveBookedDrive(@RequestBody BookedDrive bookedDriveToSave) {
        return bookedDrivesService.saveBookedDrive(bookedDriveToSave);
    }

}
