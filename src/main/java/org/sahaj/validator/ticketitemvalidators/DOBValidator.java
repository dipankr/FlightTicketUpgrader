package org.sahaj.validator.ticketitemvalidators;

import org.sahaj.data.Ticket;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

public class DOBValidator {
    public static Boolean isValid(Ticket t) {
        LocalDate curDate = LocalDate.now();
        if ((t.getDob() != null) &&
                Period.between(
                        LocalDate.ofInstant(t.getDob().toInstant(),
                                ZoneId.systemDefault()), curDate)
                        .getYears() >= 18)
            return true;
        return false;
    }
}
