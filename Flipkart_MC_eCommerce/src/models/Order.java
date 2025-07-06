package models;

import java.util.Date;
import java.util.List;

public class Order {
    private String orderId;
    private User user;
    private List<OrderItem> items;
    private String paymentMethod;
    private Date dateOfPurchase;
    private double totalAmount;
    private boolean isPaid;

    public Order(User user, List<OrderItem> items, String paymentMethod, Date dateOfPurchase, double totalAmount) {
        this.user = user;
        this.items = items;
        this.paymentMethod = paymentMethod;
        this.dateOfPurchase = dateOfPurchase;
        this.totalAmount = totalAmount;
        this.isPaid = false;
    }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public User getUser() { return user; }
    public double getTotalAmount() { return totalAmount; }
    public boolean isPaid() { return isPaid; }
    public void setPaid(boolean paid) { isPaid = paid; }

    @Override
    public String toString() {
        return orderId + " | " + user.getName() + " | " + paymentMethod + " | " + totalAmount + " | " + (isPaid ? "PAID" : "PENDING");
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

}
