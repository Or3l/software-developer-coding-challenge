package app.model;

import java.util.Objects;

public class Bid implements Comparable<Bid> {

    private User user;
    private double amount;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Bid(User user, double amount) {
        this.user = user;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bid bid = (Bid) o;
        return Double.compare(bid.amount, amount) == 0 &&
                Objects.equals(user, bid.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, amount);
    }

    @Override
    public int compareTo(Bid o) {
        return Double.compare(this.amount, o.amount);
    }
}
