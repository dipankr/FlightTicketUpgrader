package org.sahaj;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FlightTicketUpgraderTest {
    String ticketDataFilePath = "src/main/resources/ticket_data.csv";
    String successFilePath = "src/main/resources/success.csv";
    String failedFilePath = "src/main/resources/failed.csv";

    @Test
    void testUpgrader() throws Exception {
        FlightTicketUpgrader.upgrade(ticketDataFilePath, successFilePath, failedFilePath);
        assertTrue(Files.exists(Path.of(successFilePath)));
        assertTrue(Files.exists(Path.of(failedFilePath)));
    }
}