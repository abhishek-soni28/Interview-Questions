package repository;

import models.Ticket;
import java.util.HashMap;
import java.util.Map;

public class InMemoryRepository {
    private static InMemoryRepository instance;
    private final Map<String, Ticket> tickets;

    private InMemoryRepository() {
        this.tickets = new HashMap<>();
    }

    public static synchronized InMemoryRepository getInstance() {
        if (instance == null) {
            instance = new InMemoryRepository();
        }
        return instance;
    }

    public void addTicket(Ticket ticket) {
        // grenerate ticket
        /*
        1. SET COUNTER FOR TICKET NUMBER
        2. UINCREAMENT ON EVERY NEW TICKET SUCCESSFUIL ADDITION
        3. MAP THIS ID WITH THE TICKET NAME

         */
        if (tickets.containsKey(ticket.getId())) {
            System.out.println("❌ Ticket with ID " + ticket.getId() + " already exists.");
        }
        else {
            tickets.put(ticket.getId(), ticket);
            System.out.println("🎟️ Ticket Created: " + ticket);
        }
    }



    public Ticket getTicket(String id) {
        return tickets.get(id);
    }

    public void viewAllTickets() {
        System.out.println("📋 All Tickets:");
        for (Ticket ticket : tickets.values()) {
            System.out.println(ticket);
        }
    }
}
