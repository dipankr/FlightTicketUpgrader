package org.sahaj.validator.ticketitemvalidators;

import org.sahaj.data.Ticket;

/**
 * Ticketing date is before travel date
 */
public class TravelDateValidator {
    public static Boolean isValid(Ticket t) {
        if (t.getTicket_date().after(t.getTravel_date()))
            return false;
        return true;
    }
}
