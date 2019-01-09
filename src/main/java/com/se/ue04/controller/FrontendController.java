package com.se.ue04.controller;

import com.se.ue04.model.Vehicle;
import com.se.ue04.service.VehicleService;
import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


//@RestController
@Controller
//@RequestMapping(path = "/api/vehicles/")
public class FrontendController {

    private static final Logger LOG = LoggerFactory.getLogger(FrontendController.class);

    private VehicleService vehicleService;

    @Autowired
    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @RequestMapping(path = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(path = "vehicles", method = RequestMethod.POST)
    public String saveVehicle(Vehicle vehicle) {
        vehicleService.saveVehicle(vehicle);
        return "redirect:/";
    }

    @RequestMapping(path = "vehicles", method = RequestMethod.GET)
    public String getAllVehicles(Model model) {
        //model.addAttribute("vehicles", vehicleService.getAllVehicles());
        //return "vehicles";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Vehicle>> vehicleResponse = restTemplate.exchange("http://localhost:8081/api/vehicles/", HttpMethod.GET, null, new ParameterizedTypeReference<List<Vehicle>>() {});
        List<Vehicle> vehicleList = vehicleResponse.getBody();
        model.addAttribute("vehicles", vehicleList);
        return "vehicles";
    }

    @RequestMapping(path = "/vehicles/add", method = RequestMethod.GET)
    public String createVehicle(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        return "edit";
    }

    @RequestMapping(path = "/vehicles/edit/{id}", method = RequestMethod.GET)
    public String editVehicle(Model model, @PathVariable(value = "id") String id) {
        model.addAttribute("vehicle", vehicleService.getVehicle(id));
        return "edit";
    }

    @RequestMapping(path = "/vehicles/delete/{id}", method = RequestMethod.GET)
    public String deleteVehicle(@PathVariable(name = "id") String id) {
        vehicleService.deleteVehicle(id);
        return "redirect:/vehicles";
    }

    @RequestMapping(path = "/book", method = RequestMethod.GET)
    public String getAvailableVehicles(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Vehicle>> vehicleResponse = restTemplate.exchange("http://localhost:8081/api/vehicles/", HttpMethod.GET, null, new ParameterizedTypeReference<List<Vehicle>>() {});
        List<Vehicle> vehicleList = vehicleResponse.getBody();
        model.addAttribute("book", vehicleList);
        return "book";
    }


}
