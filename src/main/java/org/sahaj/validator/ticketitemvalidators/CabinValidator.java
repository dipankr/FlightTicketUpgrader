package org.sahaj.validator.ticketitemvalidators;

import org.sahaj.data.Ticket;
import org.sahaj.data.enums.CabinType;

/**
 * The booked cabin is valid (one of Economy, Premium Economy,
 *       Business, First)
 */
public class CabinValidator {
    public static Boolean isValid(Ticket t) {
        if (CabinType.INVALID.equals(t.getCabin()))
            return false;
        return true;
    }
}
