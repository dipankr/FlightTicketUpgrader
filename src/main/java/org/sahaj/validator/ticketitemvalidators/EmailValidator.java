package org.sahaj.validator.ticketitemvalidators;

import org.sahaj.data.Ticket;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Email ID is valid
 */
public class EmailValidator {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean valid(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static Boolean isValid(Ticket t) {
        if (!valid(t.getEmail()))
            return false;
        return true;
    }
}
