package services;

import models.StoryTicket;
import java.util.HashSet;
import java.util.Set;

public class SprintService {
    private final Set<StoryTicket> sprintStories;

    public SprintService() {
        this.sprintStories = new HashSet<>();
    }

    public void addStoryToSprint(StoryTicket story) {
        sprintStories.add(story);
        System.out.println("🏃‍♂️ Added to Sprint: " + story);
    }

    public void removeStoryFromSprint(String storyId) {
        boolean isPresent = sprintStories.stream().anyMatch(story -> story.getId().equals(storyId));

        if (!isPresent) {
            throw new IllegalArgumentException("Story with ID " + storyId + " not found in the sprint.");
        }

        sprintStories.removeIf(story -> story.getId().equals(storyId));
        System.out.println("🗑️ Removed from Sprint: " + storyId);
    }


    public void viewSprintStories() {
        System.out.println("📌 Sprint Stories:");
        for (StoryTicket story : sprintStories) {
            System.out.println(story);
        }
    }
}
