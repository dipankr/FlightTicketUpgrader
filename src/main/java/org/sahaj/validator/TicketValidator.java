package org.sahaj.validator;

import org.sahaj.data.Ticket;
import org.sahaj.data.enums.ValidatorResponse;
import org.sahaj.validator.ticketitemvalidators.*;

public class TicketValidator implements Validator {

    public String validate(Ticket t) {
        if (!CabinValidator.isValid(t))
            return ValidatorResponse.CABIN_INVALID.getMessage();

        if (!TravelDateValidator.isValid(t))
            return ValidatorResponse.TRAVEL_DATE_INVALID.getMessage();

        if (!EmailValidator.isValid(t))
            return ValidatorResponse.EMAIL_INVALID.getMessage();

        if (!PhoneValidator.isValid(t))
            return ValidatorResponse.PHONE_INVALID.getMessage();

        if (!PNRValidator.isValid(t))
            return ValidatorResponse.PNR_INVALID.getMessage();

        if (!DOBValidator.isValid(t))
            return ValidatorResponse.DOB_INVALID.getMessage();

        return ValidatorResponse.All_VALID.getMessage();
    }
}
