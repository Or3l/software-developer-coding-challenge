package app.model;

import java.util.Set;
import java.util.UUID;

public interface Auction {

    boolean hasBids();

    Bid getTopBid();

    void setTopBid(Bid bid);

    Set<Bid> getBids();

    double getReservePrice();

    UUID getAuctionId();


}
