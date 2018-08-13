package app.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class AuctionImpl implements Auction{

    private Item item;

    private Set<Bid> bids;

    private UUID auctionId;

    private Bid topBid;

    private double reservePrice;

    @Override
    public boolean hasBids() {
        return (bids != null && bids.size() > 0);
    }

    @Override
    public Bid getTopBid() {
        return topBid;
    }

    public AuctionImpl(Item item, UUID auctionId, double reservePrice) {
        this(item, auctionId);
        this.reservePrice = reservePrice;
    }

    private AuctionImpl(Item item, UUID auctionId) {
        this.item = item;
        this.bids = new HashSet<>();
        this.auctionId = auctionId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Set<Bid> getBids() {
        return bids;
    }

    @Override
    public double getReservePrice() {
        return reservePrice;
    }

    public UUID getAuctionId() {
        return auctionId;
    }

    public void setTopBid(Bid topBid) {
        this.topBid = topBid;
    }
}
