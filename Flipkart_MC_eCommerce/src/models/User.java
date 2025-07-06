package models;

public class User {
    private String name;
    private double bnplCredit;
    private int unpaidOrders;  // Used for blacklisting logic

    public User(String name, double bnplCredit) {
        this.name = name;
        this.bnplCredit = bnplCredit;
        this.unpaidOrders = 0;
    }

    public String getName() { return name; }
    public double getBnplCredit() { return bnplCredit; }
    public int getUnpaidOrders() { return unpaidOrders; }

    public void setBnplCredit(double bnplCredit) { this.bnplCredit = bnplCredit; }
    public void incrementUnpaidOrders() { this.unpaidOrders++; }
   // public void decrementUnpaidOrders() { this.unpaidOrders--; }
    public void clearUnpaidOrders() { this.unpaidOrders = 0; }

    public boolean isBlacklisted() { return unpaidOrders >= 3; }

    @Override
    public String toString() {
        return "User: " + name + " | BNPL Credit: " + bnplCredit + " | Unpaid Orders: " + unpaidOrders;
    }
}
