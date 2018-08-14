package app.service;

import app.data.model.Auction;
import app.data.model.Bid;

import java.util.List;

public interface IBidService {

    Bid createBid(Bid bid, Auction auction);
    Bid findBidById(long id);
    List<Bid> getAllBids();
}
