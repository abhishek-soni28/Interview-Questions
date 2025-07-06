package models;

import java.util.ArrayList;
import java.util.List;

public abstract class Ticket {
    protected String id;
    protected String title;
    protected String description;
    protected String status;
    protected final List<String> allowedStatuses;
    protected final List<String> comments;

    public Ticket(String id, String title, String description, List<String> allowedStatuses) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.allowedStatuses = allowedStatuses;
        this.status = allowedStatuses.get(0); // Default to the first status
        this.comments = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public void updateStatus(String newStatus) {
        int currentIndex = allowedStatuses.indexOf(status);
        int newIndex = allowedStatuses.indexOf(newStatus);
        String expectedStatus = allowedStatuses.get(currentIndex + 1);

        if (newIndex == -1) {
            System.out.println("❌ Invalid status: " + newStatus);
            return;
        }
        if (newIndex != currentIndex + 1) {
            System.out.println("⚠️ Status must be updated in sequence! Current: " + status +  ", Required: " + expectedStatus + ", Attempted: " + newStatus);
            return;
        }

        this.status = newStatus;
        System.out.println("✅ Status updated to: " + newStatus);
    }

    // GETtICKETNUMNER


    public void addComment(String comment) {
        comments.add(comment);
        System.out.println("📝 Comment added to '" + title + "': " + comment);
    }


    public void viewComments() {
        if (comments.isEmpty()) {
            System.out.println("⚠️ No comments for this ticket.");
            return;
        }
        System.out.println("💬 Comments for '" + title + "':");
        for (String comment : comments) {
            System.out.println("🔹 " + comment);
        }
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
