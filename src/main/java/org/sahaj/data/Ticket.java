package org.sahaj.data;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.sahaj.data.enums.CabinType;

import java.util.Date;

@Builder
@Getter
@ToString
public class Ticket {
    private String first, last, pnr, email, phone;
    private Character fareClass;
    private Date travel_date, ticket_date, dob;
    private Integer pax;
    private CabinType cabin;
}
