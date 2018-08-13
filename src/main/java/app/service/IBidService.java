package app.service;

import app.data.model.Auction;
import app.data.model.Bid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBidService {

    Bid createBid(Bid bid, Auction auction);
    Bid findBidById(long id);
    List<Bid> getAllBids();
}
