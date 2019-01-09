package com.se.ue04.controller;

import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/bookeddrives/")
@Api(value = "BookedDrivesControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookedDrivesController {

}
