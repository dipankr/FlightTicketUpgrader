package org.sahaj;

import org.sahaj.data.Ticket;
import org.sahaj.fileprocessors.read.TicketInputReader;
import org.sahaj.fileprocessors.write.TicketOutputWriter;
import org.sahaj.validator.TicketValidator;
import org.sahaj.validator.Validator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FlightTicketUpgrader {
    public static void main(String[] args) {
        System.out.println("Hello, there!");
        System.out.println("Please use testUpgrader() in FlightTicketUpgraderTest to test the complete application.");
    }

    public static void upgrade(String ticketDataFilePath, String successFilePath, String failedFilePath) throws Exception {
        upgrade(ticketDataFilePath, successFilePath, failedFilePath, false);
    }

    public static void upgrade(String ticketDataFilePath, String successFilePath, String failedFilePath, boolean appendToOldOutput) throws Exception {
        if (ticketDataFilePath.isBlank() || ticketDataFilePath.isEmpty()) return;
        if (!appendToOldOutput) deleteOldOutput(successFilePath, failedFilePath);

        Validator validator = new TicketValidator();
        TicketOutputWriter ticketOutputWriter = new TicketOutputWriter(successFilePath, failedFilePath);

        ticketOutputWriter.createWriters();

        TicketInputReader ticketFileReader = new TicketInputReader(ticketDataFilePath);
        List<Ticket> tickets = ticketFileReader.getTickets();

        for (Ticket ticket : tickets) {
            String error = validator.validate(ticket);
            ticketOutputWriter.writeOutput(error, ticket);
        }

        ticketOutputWriter.closeWriters();
    }

    private static void deleteOldOutput(String successFilePath, String failedFilePath) throws IOException {
        Files.deleteIfExists(Path.of(successFilePath));
        Files.deleteIfExists(Path.of(failedFilePath));
    }
}