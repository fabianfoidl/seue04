package com.se.ue04.controller;

import com.se.ue04.model.Fahrzeug;
import com.se.ue04.service.FahrzeugService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/fahrzeuge/")
@Api(value = "FahrzeugeControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class FahrzeugController2 {

    private FahrzeugService fahrzeugService;

    @Autowired
    public void setFahrzeugService(FahrzeugService fahrzeugService) {
        this.fahrzeugService = fahrzeugService;
    }

    private Logger LOG = LoggerFactory.getLogger(FahrzeugController.class);

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    @ApiOperation("Gets the fahrzeug with specific id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Fahrzeug.class)})
    public Fahrzeug getFahrzeug(@PathVariable(name = "id") String id) {
        return fahrzeugService.getFahrzeug(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Fahrzeug saveFahrzeug(@RequestBody Fahrzeug fahrzeugToSave) {
        return fahrzeugService.saveFahrzeug(fahrzeugToSave);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Fahrzeug updateFahrzeug(@RequestBody Fahrzeug fahrzeugToUpdate, @PathVariable(name = "id") String id) {
        return fahrzeugService.updateFahrzeug(fahrzeugToUpdate, id);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void deleteFahrzeug(@PathVariable(name = "id") String id) {
        fahrzeugService.deleteFahrzeug(id);
    }

}
