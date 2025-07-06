package services;

import models.Product;
import repository.InMemoryRepository;

public class InventoryService {
    private InMemoryRepository repo = InMemoryRepository.getInstance();

    public void seedInventory(String name, int quantity, double price) {
        repo.addProduct(name, quantity, price);
    }

    public void viewInventory() {
        repo.getAllProducts().forEach(System.out::println);
    }

    public Product getProduct(String name) {
        return repo.getProduct(name);
    }

    public void addInventory(String name, int quantity, int price) {
        repo.addInventory(name, quantity, price);
    }
    public void removeInventory(String name, int quantity) {
        repo.removeInventory(name, quantity);
    }

}


