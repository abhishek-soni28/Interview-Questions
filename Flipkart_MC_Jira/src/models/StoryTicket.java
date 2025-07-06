package models;

import java.util.*;

public class StoryTicket extends Ticket {
    private final List<SubTask> subTasks;

    public StoryTicket(String id, String title, String description) {
        super(id, title, description, List.of("Open", "In Progress", "Testing", "In Review", "Deployed"));
        this.subTasks = new ArrayList<>();
    }

    public void addSubTask(SubTask subTask) {
        subTasks.add(subTask);
        System.out.println("📌 Subtask added: " + subTask.getTitle());
    }

    public void removeSubTask(String subTaskId) {
        subTasks.removeIf(subTask -> subTask.getId().equals(subTaskId));
        System.out.println("🗑️ Subtask removed: " + subTaskId);
    }


    public void viewSubTasks() {
        if (subTasks.isEmpty()) {
            System.out.println("⚠️ No subtasks for this Story.");
            return;
        }
        System.out.println("📋 Subtasks for Story: " + getTitle());
        for (SubTask subTask : subTasks) {
            System.out.println("🔹 " + subTask.getTitle() + " [" + subTask.getStatus() + "]");
        }
    }

    @Override
    public void updateStatus(String newStatus) {
        int currentIndex = allowedStatuses.indexOf(status);
        int newIndex = allowedStatuses.indexOf(newStatus);

        String nextStatus = allowedStatuses.get(currentIndex + 1);


        if (newIndex == -1) {
            System.out.println("❌ Invalid status: " + newStatus);
            return;
        }
        if (newIndex != currentIndex + 1) {
            System.out.println("⚠️ Status must be updated in sequence! Current: " + status + ", Attempted: " + newStatus + ", Expected: " + nextStatus);
            return;
        }

        if (newStatus.equals("Deployed") && !allSubTasksCompleted()) {
            System.out.println("🚨 Cannot deploy story! Some subtasks are not yet completed.");
            return;
        }

        this.status = newStatus;

        System.out.println("✅ " + title + " Story status updated to: " + newStatus);
    }

    private boolean allSubTasksCompleted() {
        for (SubTask subTask : subTasks) {
            if (!subTask.getStatus().equals("Deployed")) {
                return false;
            }
        }
        return true;
    }
}
