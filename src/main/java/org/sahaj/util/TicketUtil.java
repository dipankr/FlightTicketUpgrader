package org.sahaj.util;

import org.sahaj.data.enums.CabinType;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TicketUtil {

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static Date getDateObject(String date) throws ParseException {
        return new SimpleDateFormat(DATE_FORMAT).parse(date);
    }

    public static String getDateString(Date date) {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        return df.format(date);
    }

    public static CabinType getCabinType(String cabin) {
        switch (CabinType.valueOf(getCabinTypeName(cabin))) {
            case ECONOMY:
                return CabinType.ECONOMY;
            case PREMIUM_ECONOMY:
                return CabinType.PREMIUM_ECONOMY;
            case BUSINESS:
                return CabinType.BUSINESS;
            case FIRST:
                return CabinType.FIRST;

            default:
                return CabinType.INVALID;
        }
    }

    private static String getCabinTypeName(String cabin) {
        return cabin.toUpperCase().replace(' ', '_');
    }
}
