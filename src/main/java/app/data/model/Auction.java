package app.data.model;

import java.util.List;

public interface Auction {

    boolean hasBids();

    Bid getTopBid();

    void setTopBid(Bid bid);

    List<Bid> getBids();

    double getReservePrice();

    long getAuctionId();


}
