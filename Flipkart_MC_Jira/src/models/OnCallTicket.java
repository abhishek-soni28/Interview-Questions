package models;

import java.util.List;

public class OnCallTicket extends Ticket {
    public OnCallTicket(String id, String title, String description) {
        super(id, title, description, List.of("Open", "In Progress", "Resolved"));
    }
}


