package app.manager;

import app.exception.AuctionAlreadyExistException;
import app.exception.AuctionDoesNotExistException;
import app.exception.InsufficientAmountException;
import app.model.Auction;
import app.model.Bid;
import app.model.Item;

import java.util.Map;
import java.util.Set;


public interface AuctionManager {
    void recordUserBid(Item item, Bid bid) throws InsufficientAmountException, AuctionDoesNotExistException;
    Set<Bid> getAllBids(Item item);
    Bid getTopBid(Item item);
    Map<Item, Auction> getItemAuctionMap();
    Auction createAuction(Item item, double reservePrice) throws AuctionAlreadyExistException;

}
