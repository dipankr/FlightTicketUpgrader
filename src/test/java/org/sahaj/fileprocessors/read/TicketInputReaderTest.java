package org.sahaj.fileprocessors.read;

import org.junit.jupiter.api.Test;
import org.sahaj.data.Ticket;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TicketInputReaderTest {
    @Test
    void testGetTickets() throws Exception {
        TicketInputReader parser = new TicketInputReader("src/main/resources/ticket_data.csv");

        List<Ticket> tickets = parser.getTickets();
        assertNotNull(tickets);
        assertTrue(5 == tickets.size());
    }
}