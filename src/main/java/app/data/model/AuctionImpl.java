package app.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AuctionImpl implements Auction{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long auctionId;

    @OneToOne(targetEntity = Car.class)
    @JoinColumn(name = "id")
    private Item item;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "auction",
            cascade = CascadeType.ALL
    )
    @JsonIgnore
    private List<Bid> bids;

    @OneToOne
    @JoinColumn(name="top_bid")
    private Bid topBid;

    private double reservePrice;

    @Override
    public boolean hasBids() {
        return (bids != null && bids.size() > 0);
    }

    @Override
    @JsonManagedReference
    public Bid getTopBid() {
        return topBid;
    }

    public AuctionImpl(Item item, double reservePrice) {
        this(item);
        this.reservePrice = reservePrice;
    }

    private AuctionImpl(Item item) {
        this.item = item;
        this.bids = new ArrayList<>();
    }

    public AuctionImpl(){}

    public Item getItem() {
        return item;
    }

    public void setItem(Car item) {
        this.item = item;
    }

    public List<Bid> getBids() {
        return bids;
    }

    @Override
    public double getReservePrice() {
        return reservePrice;
    }

    public void setTopBid(Bid topBid) {
        this.topBid = topBid;
    }

    @Override
    public long getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(long auctionId) {
        this.auctionId = auctionId;
    }
}
