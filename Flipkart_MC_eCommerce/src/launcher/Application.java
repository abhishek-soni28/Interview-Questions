package launcher;

import models.*;
import repository.InMemoryRepository;
import services.InventoryService;
import services.OrderService;
import services.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Application {
    public static void main(String[] args) throws ParseException {
        // Initialize Services
        try {
            InventoryService inventoryService = new InventoryService();
            UserService userService = new UserService();
            OrderService orderService = new OrderService();

            // Seed Inventory
            System.out.println("🚀 Seeding Inventory...");
            inventoryService.seedInventory("Shoes", 5, 2);
            inventoryService.seedInventory("Watch", 10, 1000);
            inventoryService.seedInventory("T-Shirt", 14, 2000);
            inventoryService.viewInventory();
            inventoryService.addInventory("Shoes", 50, 200);
            inventoryService.viewInventory();
            inventoryService.removeInventory("Shoes", 20);
            inventoryService.viewInventory();


            // Register User
            System.out.println("\n👤 Registering User...");
            userService.registerUser("Abhi", 5000);

            // Get User & Check BNPL Credit
            User abhi = userService.getUser("Abhi");


            // Place an order using BNPL
            System.out.println("\n🛒 Placing Order...");
            List<OrderItem> orderItems = new ArrayList<>();
            orderItems.add(new OrderItem(InMemoryRepository.getInstance().getProduct("Shoes"), 1));
            //orderItems.add(new OrderItem(InMemoryRepository.getInstance().getProduct("Watch"), 1));

            String orderId = orderService.buy(abhi, orderItems, PaymentMethod.BNPL.name(), parseDate("20-Oct-2021"));
            String orderId2 =  orderService.buy(abhi, orderItems, PaymentMethod.BNPL.name(), parseDate("20-Oct-2021"));
            String orderId3 =   orderService.buy(abhi, orderItems, PaymentMethod.BNPL.name(), parseDate("20-Oct-2021"));

            OrderService.viewDues(abhi, parseDate("21-Nov-2021"));

         //   OrderService.clearDues(abhi, Collections.singletonList(orderId), parseDate("19-Nov-2021"));
//            OrderService.viewDues(abhi, parseDate("21-Oct-2021"));
//            OrderService.clearDues(abhi, Collections.singletonList(orderId2), parseDate("19-Nov-2021"));
//            OrderService.clearDues(abhi, Collections.singletonList(orderId3), parseDate("19-Nov-2021"));
//            OrderService.viewDues(abhi, parseDate("21-Nov-2021"));
            orderService.buy(abhi, orderItems, PaymentMethod.BNPL.name(), parseDate("20-Oct-2021"));
            OrderService.clearDues(abhi, Collections.singletonList(orderId), parseDate("19-Nov-2021"));
            OrderService.viewDues(abhi, parseDate("21-Oct-2021"));
            orderService.buy(abhi, orderItems, PaymentMethod.BNPL.name(), parseDate("20-Oct-2021"));


            // View Inventory after purchase
            System.out.println("\n📦 Updated Inventory:");
            inventoryService.viewInventory();

            // View Order Status
            System.out.println("\n📜 Order Status:");
            orderService.orderStatus(abhi);

            // View Dues
            System.out.println("\n💰 Viewing Dues:");
            OrderService.viewDues(abhi, parseDate("21-Nov-2021"));

            // Clearing Dues
            System.out.println("\n💳 Clearing Dues...");
            OrderService.clearDues(abhi, Collections.singletonList(orderId), parseDate("19-Nov-2021"));



            // View Dues after clearing
            System.out.println("\n🔍 Updated Dues:");
            OrderService.viewDues(abhi, parseDate("20-Nov-2021"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
           // throw new RuntimeException(e);
        }
    }


    private static Date parseDate(String date) throws ParseException {
        return new SimpleDateFormat("dd-MMM-yyyy").parse(date);
    }
}
