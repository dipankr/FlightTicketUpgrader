package org.sahaj.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sahaj.data.enums.CabinType;
import org.sahaj.util.TicketUtil;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TicketTest {
    Ticket ticket;

    @BeforeEach
    void setup() throws ParseException {
        ticket = Ticket.builder()
                .first("ab")
                .last("cd")
                .pnr("ABC124")
                .email("abc@xyz.com")
                .phone("9876543210")
                .fareClass('A')
                .travel_date(TicketUtil.getDateObject("2019-05-30"))
                .ticket_date(TicketUtil.getDateObject("2019-05-22"))
                .pax(1)
                .cabin(CabinType.valueOf("Economy".toUpperCase()))
                .dob(TicketUtil.getDateObject("2010-05-22"))
                .build();
    }

    @Test
    void testTicketObjectNotNull() {
        assertNotNull(ticket);
    }

    @Test
    void testDatesSetAreCorrect() {
        assertTrue(ticket.getTravel_date().equals(ticket.getTravel_date()) ||
                ticket.getTravel_date().after(ticket.getTicket_date()));
    }

    @Test
    void testDOB(){
        assertTrue(ticket.getTravel_date().after(ticket.getDob()));
    }
}