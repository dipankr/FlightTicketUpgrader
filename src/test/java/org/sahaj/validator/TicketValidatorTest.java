package org.sahaj.validator;

import org.junit.jupiter.api.Test;
import org.sahaj.data.Ticket;
import org.sahaj.data.enums.CabinType;
import org.sahaj.util.TicketUtil;
import org.sahaj.validator.ticketitemvalidators.*;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TicketValidatorTest {
    Ticket ticket;

    void setupWithInvalidPNR() throws ParseException {
        ticket = Ticket.builder()
                .first("ab")
                .last("cd")
                .pnr("ABC12")
                .email("abc@xyz.com")
                .phone("9876543210")
                .fareClass('A')
                .travel_date(TicketUtil.getDateObject("2019-05-30"))
                .ticket_date(TicketUtil.getDateObject("2019-05-22"))
                .pax(1)
                .cabin(CabinType.valueOf("Economy".toUpperCase()))
                .dob(TicketUtil.getDateObject("2019-05-22"))
                .build();
    }

    void setupWithValidData() throws ParseException {
        ticket = Ticket.builder()
                .first("ab")
                .last("cd")
                .pnr("ABC123")
                .email("abc@xyz.com")
                .phone("9876543210")
                .fareClass('A')
                .travel_date(TicketUtil.getDateObject("2019-05-30"))
                .ticket_date(TicketUtil.getDateObject("2019-05-22"))
                .pax(1)
                .cabin(CabinType.valueOf("Economy".toUpperCase()))
                .build();
    }

    void setupWithInvalidData() throws ParseException {
        ticket = Ticket.builder()
                .first("ab")
                .last("cd")
                .pnr("ABC13")
                .email("abc@xyz")
                .phone("9876543")
                .fareClass('A')
                .travel_date(TicketUtil.getDateObject("2019-05-30"))
                .ticket_date(TicketUtil.getDateObject("2019-05-31"))
                .pax(1)
                .cabin(CabinType.valueOf("invalid".toUpperCase()))
                .dob(TicketUtil.getDateObject("2010-05-22"))
                .build();
    }

    @Test
    void testValidatorPNRError() throws ParseException {
        setupWithInvalidPNR();
        Validator validator = new TicketValidator();

        String error = validator.validate(ticket);
        assertTrue("PNR invalid".equals(error));
    }

    @Test
    void testValidatorNoError() throws ParseException {
        setupWithValidData();
        Validator validator = new TicketValidator();

        String error = validator.validate(ticket);
        assertTrue(error.isEmpty());
    }

    @Test
    void testAllValidators() throws ParseException {
        setupWithInvalidData();

        assertFalse(CabinValidator.isValid(ticket));
        assertFalse(TravelDateValidator.isValid(ticket));
        assertFalse(EmailValidator.isValid(ticket));
        assertFalse(PhoneValidator.isValid(ticket));
        assertFalse(PNRValidator.isValid(ticket));
    }

    @Test
    void testDOBValidator() throws ParseException {
        setupWithInvalidData();

        assertFalse(DOBValidator.isValid(ticket));
    }
}