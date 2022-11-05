package org.sahaj.fileprocessors.read;

import org.junit.jupiter.api.Test;
import org.sahaj.data.Ticket;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TicketInputReaderTest {
    String filePath = "src/main/resources/ticket_data.csv";

    @Test
    void testGetTickets() throws Exception {
        TicketInputReader parser = new TicketInputReader(filePath);

        List<Ticket> tickets = parser.getTickets();
        assertNotNull(tickets);
        assertTrue(5 == tickets.size());
    }

    @Test
    void testReadDOB() throws Exception {
        TicketInputReader parser = new TicketInputReader(filePath);
        List<Ticket> tickets = parser.getTickets();

        for(Ticket t : tickets){
            assertNotNull(t.getDob());
        }
    }
}