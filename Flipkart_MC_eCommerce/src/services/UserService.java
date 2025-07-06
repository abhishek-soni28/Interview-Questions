package services;

import models.User;
import repository.InMemoryRepository;

public class UserService {
    private InMemoryRepository repo = InMemoryRepository.getInstance();

    public void registerUser(String name, double creditLimit) {
        repo.addUser(name, creditLimit);
    }

    public User getUser(String name) {
        return repo.getUser(name);
    }
}
