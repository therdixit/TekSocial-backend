package com.teksocial.application.utility;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GlobalUtilities {
    public static  final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public static final SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    public static String formatDateAndTime(LocalDateTime dateAndTime){
        return dateAndTime.format(format);
    }

    public static final String MALE_GENDER = "MALE";
    public static  final String FEMALE_GENDER = "FEMALE";

    public static final String API_SUCCESS_STATUS = "success";
    public static final String API_FAILURE_STATUS = "error";

    public static final String FRIEND_PENDING_REQUEST = "pending";

    public static  final String FRIEND_ACCEPTED_REQUEST  = "accepted";

    public  static  final String MESSAGE_STATUS_DELIVERED = "delivered";

    public  static  final String MESSAGE_STATUS_RECEIVED = "received";


}
