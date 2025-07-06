package models;

import java.util.List;
import java.util.UUID;

public class SubTask {
    private final String id;
    private String title;
    private String status;
    private final List<String> allowedStatuses;

    public SubTask(String title) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.status = "Open";
        this.allowedStatuses = List.of("Open", "In Progress", "Testing", "In Review", "Deployed");
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    // ✅ Enforce sequential status updates
    public void updateStatus(String newStatus) {
        int currentIndex = allowedStatuses.indexOf(status);
        int newIndex = allowedStatuses.indexOf(newStatus);

        if (newIndex == -1) {
            System.out.println("❌ Invalid status: " + newStatus);
            return;
        }
        if (newIndex != currentIndex + 1) {
            System.out.println("⚠️ Subtask status must be updated in sequence! Current: " + status + ", Attempted: " + newStatus);
            return;
        }

        this.status = newStatus;
        System.out.println("✅ " + title + " Subtask status updated to: " + newStatus);
    }

    @Override
    public String toString() {
        return "SubTask{" + "id='" + id + "', title='" + title + "', status='" + status + "'}";
    }
}
