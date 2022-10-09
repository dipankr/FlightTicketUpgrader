package org.sahaj.validator.ticketitemvalidators;

import org.sahaj.data.Ticket;

/**
 * PNR is 6 characters and Is alphanumeric
 */
public class PNRValidator {
    public static boolean isAlphaNumeric(String s) {
        String pattern = "^[a-zA-Z0-9]*$";
        return s.matches(pattern);
    }

    public static Boolean isValid(Ticket t) {
        String pnr = t.getPnr();

        if (pnr.length() != 6 || !isAlphaNumeric(pnr))
            return false;
        return true;
    }
}
