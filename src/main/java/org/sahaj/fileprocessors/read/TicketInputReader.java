package org.sahaj.fileprocessors.read;

import lombok.AllArgsConstructor;
import org.sahaj.data.Ticket;
import org.sahaj.data.enums.TicketItems;
import org.sahaj.util.TicketUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class TicketInputReader {
    private String filePath;

    public List<Ticket> getTickets() throws Exception {
        List<Ticket> tickets = new ArrayList<>();
        List<String> ticketData = getTicketDataFromFile(filePath);

        //ignoring the header, starting i from 1
        for (int i = 1; i < ticketData.size(); i++) {
            Ticket ticket = parseTicket(ticketData.get(i));
            if (null != ticket)
                tickets.add(ticket);
            //System.out.println(ticket);
        }

        return tickets;
    }

    private List<String> getTicketDataFromFile(String filePath) throws FileNotFoundException {
        if (filePath.isEmpty() || filePath.isBlank()) throw new FileNotFoundException("Invalid file path.");
        List<String> ticketData = new ArrayList<>();
        try {
            File myObj = new File(filePath);
            Scanner sc = new Scanner(myObj);
            while (sc.hasNextLine()) {
                ticketData.add(sc.nextLine());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file: ");
            e.printStackTrace();
        }
        return ticketData;
    }

    private Ticket parseTicket(String ticketString) throws Exception {
        if (ticketString.isEmpty() || ticketString.isBlank()) {
            throw new Exception("Input file is not formatted correctly.");
        }
        Ticket ticket = null;
        String[] ticketItems = getTicketItems(ticketString);

        try {
            ticket = buildTicket(ticketItems);
        } catch (ParseException e) {
            System.out.println("Error parsing ticket, possibly wrong date format.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error parsing ticket.");
            e.printStackTrace();
        }

        return ticket;
    }

    private Ticket buildTicket(String[] ticketItems) throws ParseException {
        Ticket ticket = Ticket.builder()
                .first(ticketItems[TicketItems.FIRST.ordinal()])
                .last(ticketItems[TicketItems.LAST.ordinal()])
                .pnr(ticketItems[TicketItems.PNR.ordinal()])
                .fareClass(ticketItems[TicketItems.FARE.ordinal()].charAt(0))
                .travel_date(TicketUtil.getDateObject(ticketItems[TicketItems.TRAVEL_DATE.ordinal()]))
                .pax(Integer.valueOf(ticketItems[TicketItems.PAX.ordinal()]))
                .ticket_date(TicketUtil.getDateObject(ticketItems[TicketItems.TICKETING_DATE.ordinal()]))
                .email(ticketItems[TicketItems.EMAIL.ordinal()])
                .phone(ticketItems[TicketItems.MOBILE.ordinal()])
                .cabin(TicketUtil.getCabinType(ticketItems[TicketItems.CABIN.ordinal()]))
                .build();
        return ticket;
    }

    private String[] getTicketItems(String ticketString) {
        String[] ticketItems = ticketString.split(",");
        trimSpaces(ticketItems);
        return ticketItems;
    }

    private void trimSpaces(String[] items) {
        int n = items.length;
        for (int i = 0; i < n; i++)
            items[i] = items[i].trim();
    }
}
