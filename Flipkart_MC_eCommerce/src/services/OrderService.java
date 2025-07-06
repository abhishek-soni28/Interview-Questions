package services;

import models.*;
import repository.InMemoryRepository;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class OrderService {
    private InMemoryRepository repo = InMemoryRepository.getInstance();

    public String buy(User user, List<OrderItem> items, String paymentMethod, Date dateOfPurchase) {
        double totalAmount = items.stream().mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()).sum();

        if (paymentMethod.equals("BNPL")) {
            if (user.isBlacklisted() || user.getBnplCredit() < totalAmount) {
                System.out.println("❌ BNPL not allowed!");
                return null;
            }
            user.incrementUnpaidOrders();
            user.setBnplCredit(user.getBnplCredit() - totalAmount);
        }

        for (OrderItem item : items) {
            if(repo.getProduct(item.getProduct().getName()).getQuantity() < item.getQuantity())
            {
                System.out.println("Requested Order qantity is greater than wt in stock " + repo.getProduct(item.getProduct().getName()).getName() + repo.getProduct(item.getProduct().getName()).getQuantity());
                throw new IllegalArgumentException();
            }
            repo.getProduct(item.getProduct().getName()).setQuantity(repo.getProduct(item.getProduct().getName()).getQuantity() - item.getQuantity());
        }

        Order order = new Order(user, items, paymentMethod, dateOfPurchase, totalAmount);
        return repo.addOrder(order);
    }

    /** View Dues for a User **/
    public static void viewDues(User user, Date date) {
        double totalAmount = 0;

        boolean flag = true;
        InMemoryRepository repo = InMemoryRepository.getInstance();
        for (Order order : repo.getAllOrders()) {
            if (order.getUser().equals(user) && !order.isPaid() && order.getDateOfPurchase().before(date)) {
                totalAmount += order.getTotalAmount();
                flag = false;
                System.out.println(order);
            }
        }
        System.out.println("Total Dues for: " + user.getName() + " is " + totalAmount);

        if(flag)
        {
            System.out.println("No Pending due for " + user.getName() + ". Thanks 😊");
        }
    }

    /** Clear Dues for Orders **/
    public static void clearDues(User user, List<String> orderIds, Date date) {
        InMemoryRepository repo = InMemoryRepository.getInstance();
        for (String orderId : orderIds) {
            Order order = repo.getOrder(orderId);
            if (order != null && order.getUser().equals(user) && !order.isPaid() && order.getDateOfPurchase().before(date)) {
                order.setPaid(true);
                user.clearUnpaidOrders();
                System.out.println("✅ Cleared: " + order);
            }
        }
    }

    public void orderStatus(User user) {
        List<Order> orders = repo.getOrdersByUser(user);
        System.out.println("BNPL Credit Limit Available: " + user.getBnplCredit());

        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }

        orders.sort(Comparator.comparing(Order::getDateOfPurchase)); // Sort by date
        for (Order order : orders) {
            System.out.println(order);
        }
    }

}
