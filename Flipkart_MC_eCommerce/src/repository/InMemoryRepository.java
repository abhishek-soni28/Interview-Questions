package repository;

import models.Order;
import models.Product;
import models.User;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryRepository {
    private static InMemoryRepository instance;
    private Map<String, User> users = new HashMap<>();
    private Map<String, Order> orders = new HashMap<>();
    private Map<String, Product> inventory = new HashMap<>();
    private static int orderCounter = 1;



    private InMemoryRepository() {} // Private constructor for Singleton

    public static synchronized InMemoryRepository getInstance() {
        if (instance == null) {
            instance = new InMemoryRepository();
        }
        return instance;
    }

    /*** Inventory Management ***/
    public void addProduct(String name, int quantity, double price) {
        if(quantity <= 0 || price <= 0) {
            throw new IllegalArgumentException("Enter valid Quantity or price");
        }
        inventory.put(name, new Product(name, quantity, price));
    }

    public void addInventory(String name, int quantity, int price) {
        if(quantity <= 0 || price <= 0) {
            throw new IllegalArgumentException("Enter valid Quantity or price");
        }
        Product product = inventory.get(name); // Get existing product
        if (product != null) {
            product.setQuantity(product.getQuantity() + quantity); // Modify the object
            product.setPrice(price); // Modify the object
        }

    }
    public void removeInventory(String name, int quantity)
    {
        Product product = inventory.get(name); // Get existing product
        if (product != null && product.getQuantity() > quantity) {

                product.setQuantity(product.getQuantity() - quantity); // Modify the object
            }
          else
        {
            throw new IllegalArgumentException("Invalid Product name or available quantitu ois less than removal inventory");

        }




    }

    public Product getProduct(String name) {
        return inventory.get(name);
    }

    public Collection<Product> getAllProducts() {
        return inventory.values();
    }

    /*** User Management ***/
    public void addUser(String name, double creditLimit) {
        users.put(name, new User(name, creditLimit));
        System.out.println("✅ User Registered: " + name);

    }

    public User getUser(String name) {
        return users.get(name);
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }


    /*** Order Management ***/
    public String addOrder(Order order) {
        String orderId = "OD_" + orderCounter++;
        order.setOrderId(orderId);
        orders.put(orderId, order);
        System.out.println("✅ Order Placed! Order ID: " + orderId);

        return orderId;
    }

    public Order getOrder(String orderId) {
        return orders.get(orderId);
    }

    public List<Order> getOrdersByUser(User user) {
        return orders.values().stream()
                .filter(order -> order.getUser().equals(user))
                .collect(Collectors.toList());
    }

}
