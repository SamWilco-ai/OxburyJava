package com.wilcock.samuel.oxbury.util;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class helper {
    public static java.sql.Date convertToDate(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = sdf.parse(dateStr);
            return new java.sql.Date(parsedDate.getTime()); // Convert to java.sql.Date
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format: " + dateStr, e);
        }
    }
}
