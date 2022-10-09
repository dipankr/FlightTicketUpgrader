package org.sahaj.validator.ticketitemvalidators;

import org.sahaj.data.Ticket;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The mobile phone is valid
 */
public class PhoneValidator {
    public static final Pattern VALID_PHONE_REGEX =
            Pattern.compile("(0/91)?[7-9][0-9]{9}", Pattern.CASE_INSENSITIVE);

    public static boolean valid(String emailStr) {
        Matcher matcher = VALID_PHONE_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static Boolean isValid(Ticket t) {
        if (!valid(t.getPhone()))
            return false;
        return true;
    }
}
