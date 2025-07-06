package models;

import java.util.List;

public class EpicTicket extends Ticket {
    public EpicTicket(String id, String title, String description) {
        super(id, title, description, List.of("Open", "In Progress", "Completed"));
    }
}
