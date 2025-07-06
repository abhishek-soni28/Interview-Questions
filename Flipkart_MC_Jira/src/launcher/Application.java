package launcher;

import models.*;
import services.TicketService;
import services.SprintService;

public class Application {
    public static void main(String[] args) {
        try {
            TicketService ticketService = new TicketService();
            SprintService sprintService = new SprintService();

            //  Create Tickets
            StoryTicket story = new StoryTicket("T1", "Implement Login", "Build login feature");
            EpicTicket epic = new EpicTicket("T2", "User Authentication", "Manage auth system");
            OnCallTicket onCall = new OnCallTicket("T3", "Fix Production Bug", "Resolve payment issue");
    // ANOTHER TICKET
            ticketService.createTicket(story);
            ticketService.createTicket(story);
            ticketService.createTicket(epic);
            ticketService.createTicket(onCall);
            ticketService.showAllTickets();

            //  Update Ticket Status - Valid & Invalid Cases
            System.out.println("\n🔄 Testing Status Updates...");
            ticketService.updateTicketStatus("T1", "In Progress");
            ticketService.updateTicketStatus("T1", "Testing");
            ticketService.updateTicketStatus("T1", "Testing");
            ticketService.updateTicketStatus("T1", "Deployed");
            ticketService.updateTicketStatus("T1", "In Review");
           // ticketService.updateTicketStatus("T1", "Deployed"); // ✅ Allowed

            ticketService.showAllTickets();

            System.out.println("\n💬 Adding Comments...");
            story.addComment("Initial setup completed.");
            story.addComment("UI design approved.");
            story.addComment("API integration pending.");

            /*
            req time based sorting
                custom comparator

                1 2 3
                commemnt : id , isdeleted
                stack   5 1 2 3


                deleteCommet(id)
                    tickert id deletews= true;


                  // time based
             */


            System.out.println("\n📜 Viewing Comments:");
            story.viewComments();

            //  Sprint Management
            System.out.println("\n🏃‍♂️ Sprint Management Tests...");
            sprintService.addStoryToSprint(story);
            // sprintService.addStoryToSprint(story);
            sprintService.viewSprintStories();

            // Trying to remove a non-existent story
           // sprintService.removeStoryFromSprint("T999");
            sprintService.removeStoryFromSprint("T1");

            //  Invalid Ticket ID Handling
            System.out.println("\n❌ Testing Invalid Ticket IDs...");
            ticketService.updateTicketStatus("T999", "In Progress");

            //  Subtask Management
            System.out.println("\n📌 Subtask Management...");
            SubTask subtask1 = new SubTask("Design UI");
            SubTask subtask2 = new SubTask("API Development");

            story.addSubTask(subtask1);
            story.addSubTask(subtask2);

            // View Subtasks
            System.out.println("\n📝 Viewing Subtasks:");
            story.viewSubTasks();

            // Update Subtask Status
            System.out.println("\n🔄 Updating Subtask Status...");
            subtask1.updateStatus("In Progress");
            subtask1.updateStatus("Testing");
            subtask2.updateStatus("In Progress");

            // Trying to Close Story While Subtasks Are Incomplete
            System.out.println("\n🚨 Attempting to Deploy Story with Incomplete Subtasks...");
            ticketService.updateTicketStatus("T1", "Deployed"); // ❌ Should fail due to unfinished subtasks

            // Complete All Subtasks
            subtask1.updateStatus("In Review");
            subtask1.updateStatus("Deployed");
            subtask2.updateStatus("Testing");
            subtask2.updateStatus("In Review");
            subtask2.updateStatus("Deployed");

            System.out.println("\n✅ Now Deploying Story After Completing Subtasks...");
            story.viewSubTasks();
            ticketService.updateTicketStatus("T1", "Deployed"); // ✅ Should succeed now

            // Deleting a Subtask
            System.out.println("\n🗑️ Deleting a Subtask...");
            story.removeSubTask(subtask1.getId());

            // 📋 Final Ticket Status
            System.out.println("\n✅ Final State of All Tickets:");
            ticketService.updateTicketStatus("T2", "In Progress");
            ticketService.updateTicketStatus("T2", "Completed");
            ticketService.updateTicketStatus("T3", "In Progress");
            ticketService.updateTicketStatus("T3", "Resolved");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
