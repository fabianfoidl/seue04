package com.se.ue04.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.ue04.Constants;
import com.se.ue04.model.BookedDrive;
import com.se.ue04.model.Vehicle;
import com.se.ue04.service.BookedDrivesService;
import com.se.ue04.service.VehicleService;
import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;


//@RestController
@Controller
//@RequestMapping(path = "/api/vehicles/")
public class FrontendController {

    private static final Logger LOG = LoggerFactory.getLogger(FrontendController.class);

    private VehicleService vehicleService;
    private BookedDrivesService bookedDrivesService;

    @Autowired
    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @Autowired
    public void setBookedDrivesService(BookedDrivesService bookedDrivesService) {
        this.bookedDrivesService = bookedDrivesService;
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

    // TODO: auf Service auslagern
    @RequestMapping(path = "/book", method = RequestMethod.GET)
    public String getAvailableDrives(HttpServletRequest request, Model model) {
        // set request params
        String noGuest = request.getParameter("noguest");
        String pickup = request.getParameter("pickup");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        RestTemplate restTemplate = new RestTemplate();


        if (noGuest != null && pickup != null && date != null && time != null) {
            String route = (Constants.START_HOTEL.equals(pickup)) ? "Hotel -> Airport" : "Airport -> Hotel";
            String dateTime = date + " - " + time;
            model.addAttribute("route", route);
            model.addAttribute("datetime", dateTime);
            model.addAttribute("neededseats", noGuest);

            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://localhost:8081/api/availablerides/")
                    .queryParam("date", date)
                    .queryParam("time", time)
                    .queryParam("seats", noGuest)
                    .queryParam("route", pickup);
            ResponseEntity<List<Vehicle>> vehicleResponse = restTemplate.exchange(builder.toUriString(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Vehicle>>() {});
            List<Vehicle> vehicleList = vehicleResponse.getBody();
            //model.addAttribute("availableDrives", vehicleList);
            model.addAttribute("noguest", noGuest);
            model.addAttribute("pickup", pickup);
            model.addAttribute("date", date);
            model.addAttribute("time", time);




            model.addAttribute("availableDrives", vehicleList);
        } else {
            LOG.info("Some parameter is not set - displaying start page");
        }
        // has to be named like html file
        return "book";
    }

    @RequestMapping(path = "/booksummary", method = RequestMethod.POST)
    public String getBookedRideSummary(HttpServletRequest request, Model model) {
        // set request params
        String noGuest = request.getParameter("noguest");
        String pickup = request.getParameter("pickup");
        String dateString = request.getParameter("date");
        String timeString = request.getParameter("time");
        String dateTime = dateString + " - " + timeString;
        String selectedVehicleId = request.getParameter("selectedvehicle");

        Vehicle vehicle = vehicleService.getVehicle(selectedVehicleId);

        model.addAttribute("noguest", noGuest);
        model.addAttribute("route", pickup);
        model.addAttribute("datetime", dateTime);
        model.addAttribute("vehicle", vehicle);

        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy HHmm");
        Date date = null;
        try {
            date = sdf.parse(dateString + " " + timeString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        BookedDrive bookedDrive = new BookedDrive();
        bookedDrive.setNoGuests(Integer.parseInt(noGuest));
        bookedDrive.setRoute(pickup);
        bookedDrive.setTime(date);
        bookedDrive.setVehicle(selectedVehicleId);

        bookedDrivesService.saveBookedDrive(bookedDrive);

        return "booksummary";
    }


}
