package com.se.ue04;

import com.se.ue04.model.Vehicle;
import com.se.ue04.service.FrontendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {

    private static Logger LOG = LoggerFactory.getLogger(Helper.class);

    public static boolean isStringWithTimeInside(String time) {
        Pattern p = Pattern.compile("([01]?[0-9]|2[0-3]):[0-5][0-9]");
        Matcher m = p.matcher(time);
        if(m.matches()){
            return true;
        }
        return false;
    }

    public static boolean isStringWithDateInside(String date) {
        Pattern p = Pattern.compile("[0-3][1-9].[0-1][1-12].[1-2][0-9][0-9][0-9]");
        Matcher m = p.matcher(date);
        if(m.matches()){
            return true;
        }
        return false;
    }

    public static boolean isDateValid(String date) {
        try {
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Encrypt Password with BCryptPasswordEncoder
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static Vehicle getVehicleById(String id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Vehicle> vehicleResponse = restTemplate.exchange(Constants.APIURL + "/vehicles/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Vehicle>() {});
        return vehicleResponse.getBody();
    }

    public static String formatDate(Date date) {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return df.format(date);
    }

}
