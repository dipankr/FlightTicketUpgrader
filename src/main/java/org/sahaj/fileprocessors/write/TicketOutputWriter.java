package org.sahaj.fileprocessors.write;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.sahaj.data.Ticket;
import org.sahaj.util.TicketUtil;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

@RequiredArgsConstructor
public class TicketOutputWriter {
    @NonNull
    private String successFilePath, failedFilePath;
    private PrintWriter success, failed;
    private boolean successFileExists, failedFileExists;

    public void createWriters() throws IOException {
        setupFiles();

        FileWriter successFW = new FileWriter(successFilePath, true);
        BufferedWriter successBW = new BufferedWriter(successFW);
        success = new PrintWriter(successBW);

        FileWriter failedFW = new FileWriter(failedFilePath, true);
        BufferedWriter failedBW = new BufferedWriter(failedFW);
        failed = new PrintWriter(failedBW);

        setupHeaders();
    }

    private void setupFiles() throws IOException {
        successFileExists = Files.exists(Path.of(successFilePath));
        failedFileExists = Files.exists(Path.of(failedFilePath));

        if (!successFileExists) {
            Files.createFile(Path.of(successFilePath));
        }

        if (!failedFileExists) {
            Files.createFile(Path.of(failedFilePath));
        }
    }

    private void setupHeaders() {
        if (!successFileExists) setupSuccessHeader();
        if (!failedFileExists) setupFailedHeader();
    }

    private void setupSuccessHeader() {
        success.println("FIRST_NAME, LAST_NAME, PNR, FARE_CLASS, TRAVEL_DATE, PAX, TICKETING_DATE, EMAIL, MOBILE_PHONE, BOOKED_CABIN, DISCOUNT_CODE");
    }

    private void setupFailedHeader() {
        failed.println("FIRST_NAME, LAST_NAME,PNR, FARE_CLASS, TRAVEL_DATE, PAX, TICKETING_DATE, EMAIL, MOBILE_PHONE, BOOKED_CABIN, ERROR");
    }

    public void writeOutput(String error, Ticket t) {
        if (error.isBlank())
            writeSuccess(t);
        else
            writeFailed(t, error);
    }

    private void writeSuccess(Ticket t) {
        StringBuilder output = getTicketStringBuilder(t).append(getDiscountCode(t.getFareClass()));
        success.println(output.toString());
    }

    private void writeFailed(Ticket t, String error) {
        StringBuilder output = getTicketStringBuilder(t).append(error);
        failed.println(output.toString());
    }

    private StringBuilder getTicketStringBuilder(Ticket t) {
        return new StringBuilder()
                .append(t.getFirst()).append(", ")
                .append(t.getLast()).append(", ")
                .append(t.getPnr()).append(", ")
                .append(t.getFareClass()).append(", ")
                .append(TicketUtil.getDateString(t.getTravel_date())).append(", ")
                .append(t.getPax()).append(", ")
                .append(TicketUtil.getDateString(t.getTicket_date())).append(", ")
                .append(t.getEmail()).append(", ")
                .append(t.getPhone()).append(", ")
                .append(formatCabin(t.getCabin().name())).append(", ");
    }

    private String formatCabin(String cabin) {
        if(-1 == cabin.indexOf('_'))
            return cabin.substring(0,1).toUpperCase() + cabin.substring(1).toLowerCase();

        StringBuilder cabinSB = new StringBuilder();
        for(String cabinNamePart : cabin.split("_")){
            cabinSB.append(cabinNamePart.substring(0,1).toUpperCase() + cabinNamePart.substring(1).toLowerCase());
            cabinSB.append(" ");
        }
        return cabinSB.toString();
    }

    private String getDiscountCode(Character fareClass) {
        if (fareClass >= 'A' && fareClass <= 'E') return "OFFER_20";
        if (fareClass >= 'F' && fareClass <= 'K') return "OFFER_30";
        if (fareClass >= 'L' && fareClass <= 'R') return "OFFER_25";
        return "";
    }

    public void closeWriters() {
        success.close();
        failed.close();
    }
}
