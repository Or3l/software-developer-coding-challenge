package app.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Bid implements Comparable<Bid> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bidId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private double amount;

    @ManyToOne(targetEntity = AuctionImpl.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_id")
    @JsonIgnore
    private Auction auction;

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

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

    public Bid(){

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

    public long getBidId() {
        return bidId;
    }
}
