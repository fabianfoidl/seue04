package com.se.ue04.service;

import com.se.ue04.Constants;
import com.se.ue04.model.BookedDrive;
import com.se.ue04.model.User;
import com.se.ue04.model.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class FrontendService {

    private static final Logger LOG = LoggerFactory.getLogger(FrontendService.class);

    private RestTemplate restTemplate = new RestTemplate();

    private boolean loggedIn;

    public List<Vehicle> getAllVehicles() {
        ResponseEntity<List<Vehicle>> vehicleResponse = restTemplate.exchange(Constants.APIURL + "/vehicles/",
                        HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Vehicle>>() {});
        return vehicleResponse.getBody();
    }

    public Vehicle getVehicleById(String id) {
        ResponseEntity<Vehicle> vehicleResponse = restTemplate.exchange(Constants.APIURL + "/vehicles/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Vehicle>() {});
        return vehicleResponse.getBody();
    }

    public void saveVehicle(Vehicle vehicle) {
        restTemplate.postForLocation(Constants.APIURL + "/vehicles/", vehicle);
    }

    public void deleteVehicleById(String id) {
        restTemplate.delete(Constants.APIURL + "/vehicles/" + id);
    }

    public List<Vehicle> getAvailableVehicles(String date, String time, String noGuest, String pickup) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(Constants.APIURL + "/availablerides/")
                .queryParam("date", date)
                .queryParam("time", time)
                .queryParam("seats", noGuest)
                .queryParam("route", pickup);

        ResponseEntity<List<Vehicle>> vehicleResponse = restTemplate.exchange(builder.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Vehicle>>() {});
        List<Vehicle> vehicleList = vehicleResponse.getBody();
        return vehicleList;
    }

    public void saveRide(String dateString, String timeString, String noGuest, String pickup, String selectedVehicleId) {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy HHmmss");
        Date date;
        try {
            date = sdf.parse(dateString + " " + timeString);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        BookedDrive bookedDrive = new BookedDrive();
        bookedDrive.setNoGuests(Integer.parseInt(noGuest));
        bookedDrive.setRoute(pickup);
        bookedDrive.setTime(date);
        bookedDrive.setVehicle(selectedVehicleId);

        restTemplate.postForLocation(Constants.APIURL + "/bookeddrives/", bookedDrive);
    }

    public void saveUser(User user) {
        restTemplate.postForLocation(Constants.APIURL + "/login/create", user);
    }

    public User getUserByEmail(String email) {
        ResponseEntity<User> userResponse = restTemplate.exchange(Constants.APIURL + "/login/" + email,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<User>() {});
        return userResponse.getBody();
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
