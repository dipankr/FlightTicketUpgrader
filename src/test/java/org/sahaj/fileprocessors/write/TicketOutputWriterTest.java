package org.sahaj.fileprocessors.write;

import org.junit.jupiter.api.Test;
import org.sahaj.data.Ticket;
import org.sahaj.data.enums.CabinType;
import org.sahaj.util.TicketUtil;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TicketOutputWriterTest {
    String successFilePath = "src/main/resources/test/success_test.csv";
    String failedFilePath = "src/main/resources/test/failed_test.csv";

    Ticket ticket;
    TicketOutputWriter ticketOutputWriter;

    void setup() throws ParseException, IOException {
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
                .build();

        ticketOutputWriter = new TicketOutputWriter(successFilePath, failedFilePath);
        ticketOutputWriter.createWriters();
    }

    void teardown() throws IOException {
        ticketOutputWriter.closeWriters();
    }

    @Test
    void testConstructor() throws ParseException, IOException {
        setup();

        assertNotNull(ticketOutputWriter);

        teardown();
    }

    @Test
    void testOutputWriterFailed() throws IOException, ParseException {
        setup();

        ticketOutputWriter.writeOutput("PNR invalid", ticket);

        teardown();
    }

    @Test
    void testOutputWriterSuccess() throws IOException, ParseException {
        setup();

        ticketOutputWriter.writeOutput("", ticket);

        teardown();
    }
}