package com.se.ue04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

}
