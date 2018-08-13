package app.manager;

import app.exception.AuctionAlreadyExistException;
import app.exception.AuctionDoesNotExistException;
import app.exception.InsufficientAmountException;
import app.data.model.Auction;
import app.data.model.Bid;
import app.data.model.Item;

import java.util.List;
import java.util.Map;


public interface AuctionManager {
    void recordUserBid(Item item, Bid bid) throws InsufficientAmountException, AuctionDoesNotExistException;
    List<Bid> getAllBids(Item item);
    Bid getTopBid(Item item);
    Map<Item, Auction> getItemAuctionMap();
    Auction createAuction(Item item, double reservePrice) throws AuctionAlreadyExistException;

}
