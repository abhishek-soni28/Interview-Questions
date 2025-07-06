package services;

import models.Ticket;
import repository.InMemoryRepository;

public class TicketService {
    private final InMemoryRepository repository;
    private static int ticketCounter = 0;


    public TicketService() {
        this.repository = InMemoryRepository.getInstance();
    }

    public void createTicket(Ticket ticket) {

        repository.addTicket(ticket);

        ticketCounter++;
    }

    public void updateTicketStatus(String ticketId, String newStatus) {
        Ticket ticket = repository.getTicket(ticketId);
        if (ticket != null) {
            ticket.updateStatus(newStatus);
        } else {
            System.out.println("❌ Ticket not found: " + ticketId);
        }
    }

    public void showAllTickets() {
        repository.viewAllTickets();
    }
}
