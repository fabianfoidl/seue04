package com.se.ue04.controller;

import com.se.ue04.Constants;
import com.se.ue04.Helper;
import com.se.ue04.model.User;
import com.se.ue04.model.Vehicle;
import com.se.ue04.service.FrontendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.*;


@Controller
public class FrontendController {

    private static final Logger LOG = LoggerFactory.getLogger(FrontendController.class);

    private FrontendService frontendService;

    @Autowired
    public void setFrontendService(FrontendService frontendService) {
        this.frontendService = frontendService;
    }

    // INDEX

    @RequestMapping(path = "/")
    public String index(Model model, Principal principal) {
        if (principal != null) {
            User user = frontendService.getUserByEmail(principal.getName());
            model.addAttribute("user", user);
        }
        return "index";
    }

    // manage vehicles

    @RequestMapping(path = "vehicles", method = RequestMethod.GET)
    public String getAllVehicles(Model model) {
        List<Vehicle> vehicleList = frontendService.getAllVehicles();
        model.addAttribute("vehicles", vehicleList);
        return "vehicles";
    }

    @RequestMapping(path = "/vehicles/add", method = RequestMethod.GET)
    public String createVehicle(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        return "edit";
    }

    // save new vehicle
    @RequestMapping(path = "vehicles", method = RequestMethod.POST)
    public String saveVehicle(@Valid Vehicle vehicle, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("addvehiclefail", true);
            return "edit";
        }
        frontendService.saveVehicle(vehicle);
        return "redirect:/vehicles";
    }

    @RequestMapping(path = "/vehicles/edit/{id}", method = RequestMethod.GET)
    public String editVehicle(Model model, @PathVariable(value = "id") String id) {
        Vehicle vehicle = frontendService.getVehicleById(id);
        // if no vehicle was found, redirect to vehicle page
        if (vehicle == null) {
            return "redirect:/vehicles";
        }
        model.addAttribute("vehicle", vehicle);
        return "edit";
    }

    @RequestMapping(path = "/vehicles/delete/{id}", method = RequestMethod.GET)
    public String deleteVehicle(@PathVariable(name = "id") String id) {
        frontendService.deleteVehicleById(id);
        return "redirect:/vehicles";
    }

    // BOOK VEHICLE

    @RequestMapping(path = "/book", method = RequestMethod.GET)
    public String getAvailableDrives(HttpServletRequest request, Model model) {

        // if no param is set, load default page
        if (request.getParameter("noguest") == null && request.getParameter("pickup") == null && request.getParameter("date") == null && request.getParameter("time") == null) {
            return "book";
        }

        boolean paramFail = false;

        // set request params
        String noGuest = "";
        String pickup = "";
        String date = "";
        String dateValue = "";
        String time = "";
        String timeValue = "";
        RestTemplate restTemplate = new RestTemplate();

        // check set and format of params

        // Param number of guests
        if (request.getParameter("noguest") == null) {
            model.addAttribute("noguestfail", true);
            paramFail = true;
        } else {
            if (request.getParameter("noguest").equals("")) {
                model.addAttribute("noguestfail", true);
                paramFail = true;
            } else {
                noGuest = request.getParameter("noguest");
                model.addAttribute("noguestfail", false);
                model.addAttribute("noguest", noGuest);
            }
        }

        if (request.getParameter("pickup") == null) {
            model.addAttribute("pickupfail", true);
            paramFail = true;
        } else {
            if (request.getParameter("pickup").equals("")) {
                model.addAttribute("pickupfail", true);
                paramFail = true;
            } else {
                pickup = request.getParameter("pickup");
                model.addAttribute("pickupfail", false);
                model.addAttribute("pickup", pickup);
            }
        }

        // Param date
        if (request.getParameter("date") == null) {
            model.addAttribute("datefail", true);
            paramFail = true;
        } else {
            if (request.getParameter("date").equals("")) {
                model.addAttribute("datefail", true);
                paramFail = true;
            } else {
                date = request.getParameter("date");
                model.addAttribute("datefail", false);
                if (!Helper.isStringWithDateInside(date)) {
                    model.addAttribute("dateformatfail", true);
                    paramFail = true;
                } else {
                    if (Helper.isDateValid(date)) {
                        dateValue = date;
                        dateValue = dateValue.replace(".", "");
                        model.addAttribute("dateformatfail", false);
                        model.addAttribute("date", date);
                        model.addAttribute("datevalue", dateValue);
                    } else {
                        model.addAttribute("dateformatfail", true);
                        paramFail = true;
                    }
                }
            }
        }

        // Param time
        if (request.getParameter("time") == null) {
            model.addAttribute("timefail", true);
            paramFail = true;
        } else {
            if (request.getParameter("time").equals("")) {
                model.addAttribute("timefail", true);
                paramFail = true;
            } else {
                time = request.getParameter("time");
                model.addAttribute("timefail", false);
                if (!Helper.isStringWithTimeInside(time)) {
                    model.addAttribute("timeformatfail", true);
                    paramFail = true;
                } else {
                    timeValue = time;
                    timeValue = timeValue.replace(":", "");
                    if (timeValue.length() == 3) timeValue = "0" + timeValue;
                    // format for database
                    timeValue += "00";
                    model.addAttribute("timeformatfail", false);
                    model.addAttribute("time", time);
                    model.addAttribute("timevalue", timeValue);
                }
            }
        }

        // if there was a failure with params, don't return result
       if (paramFail) {
           model.addAttribute("showresult", false);
            return "book";
       }

       model.addAttribute("showresult", true);

       // no further checking needed
       String route = (Constants.START_HOTEL.equals(pickup)) ? "Hotel -> Airport" : "Airport -> Hotel";
       String dateTime = date + " - " + time;
       model.addAttribute("route", route);
       model.addAttribute("datetime", dateTime);
       model.addAttribute("neededseats", noGuest);

       List<Vehicle> vehicleList = frontendService.getAvailableVehicles(dateValue, timeValue, noGuest, pickup);
       model.addAttribute("availableDrives", vehicleList);

       // has to be named like html file
       return "book";
    }

    @RequestMapping(path = "/booksummary", method = RequestMethod.POST)
    public String getBookedRideSummary(HttpServletRequest request, Model model, Principal principal) {
        // set request params
        String noGuest;
        String pickup;
        String dateString;
        String dateReq;
        String timeString;
        String timeReq;
        String dateTime;
        String selectedVehicleId;

        try {
            noGuest = request.getParameter("noguest");
            pickup = request.getParameter("pickup");
            dateString = request.getParameter("date");
            dateReq = request.getParameter("datevalue");
            timeString = request.getParameter("time");
            timeReq = request.getParameter("timevalue");
            dateTime = dateString + " - " + timeString;
            selectedVehicleId = request.getParameter("selectedvehicle");
        } catch (Exception e) {
            e.printStackTrace();
            return "book";
        }

        if (noGuest == "" || pickup == "" || dateString == "" || timeString == "" || selectedVehicleId == "" || dateReq == "" || timeReq == "") {
            return "book";
        }

        Vehicle vehicle = frontendService.getVehicleById(selectedVehicleId);

        model.addAttribute("noguest", noGuest);
        model.addAttribute("route", (pickup.equals(Constants.START_HOTEL) ? "Hotel -> Airport" : "Airport -> Hotel"));
        model.addAttribute("datetime", dateTime);
        model.addAttribute("vehicle", vehicle);

        frontendService.saveRide(dateReq, timeReq, noGuest, pickup, selectedVehicleId, principal.getName());

        return "booksummary";
    }

    // LOGIN

    @RequestMapping(path = "login", method = RequestMethod.GET)
    public String getLogin(Model model) {
        return "login";
    }

    @RequestMapping(path = "login", method = RequestMethod.POST)
    public String register(User user, Model model) {
        user.setPassword(Helper.encryptPassword(user.getPassword()));
        frontendService.saveUser(user);
        if (frontendService.getUserByEmail(user.getEmail()) != null) {
            model.addAttribute("alreadyregistered", true);
            model.addAttribute("email", user.getEmail());
            return "register";
        }
        model.addAttribute("registerSuccess", true);
        return "login";
    }

    @RequestMapping(path = "/login/register", method = RequestMethod.GET)
    public String getRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(path = "rides", method = RequestMethod.GET)
    public String getRides(Model model, Principal principal) {
        User user = frontendService.getUserByEmail(principal.getName());
        model.addAttribute("bookeddrivesheading", "Booked rides for " + user.getName());
        model.addAttribute("rides", frontendService.getBookedDrivesByUser(user.getEmail()));
        return "rides";
    }

    @RequestMapping(path = "/rides/{id}", method = RequestMethod.GET)
    public String deleteRides(@PathVariable(name = "id") String id) {
        frontendService.deleteRide(id);
        return "redirect:/rides";
    }

    @RequestMapping(path = "layout/header", method = RequestMethod.GET)
    public String getHeader(Model model) {
        return "layout/header";
    }

    @RequestMapping(path = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model) {
       return "403";
    }

}
