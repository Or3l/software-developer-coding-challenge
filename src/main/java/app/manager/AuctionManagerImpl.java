package app.manager;

import app.exception.AuctionAlreadyExistException;
import app.exception.AuctionDoesNotExistException;
import app.exception.InsufficientAmountException;
import app.model.Auction;
import app.model.AuctionImpl;
import app.model.Bid;
import app.model.Item;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class AuctionManagerImpl implements AuctionManager {

    private Map<Item, Auction> itemAuctionMap;

    public AuctionManagerImpl() {
        itemAuctionMap = new ConcurrentHashMap<>();
    }

    @Override
    public void recordUserBid(Item item, Bid bid) throws InsufficientAmountException, AuctionDoesNotExistException {
        //if no auction started for that item
        Auction auction = itemAuctionMap.get(item);
        if(auction == null){
            throw new AuctionDoesNotExistException("There are no auction for the given item");
        }
        if(Double.compare(bid.getAmount(), auction.getReservePrice()) < 0){
            throw new InsufficientAmountException("The amount is less than the reserve price");
        }
        if(auction.getTopBid() != null) {
            if (bid.compareTo(auction.getTopBid()) > 0) {
                updateAuction(bid, auction);
            } else {
                throw new InsufficientAmountException("The amount is less or equal to the topBid");
            }
        } else {
            updateAuction(bid, auction);
        }
        //add the new bid to the list of existing bids
        itemAuctionMap.put(item, auction);
    }

    private void updateAuction(Bid bid, Auction auction) {
        auction.setTopBid(bid);
        auction.getBids().add(bid);
    }

    @Override
    public Set<Bid> getAllBids(Item item) {
        return itemAuctionMap.get(item).getBids();
    }

    @Override
    public Bid getTopBid(Item item) {
        return itemAuctionMap.get(item).getTopBid();
    }

    public Map<Item, Auction> getItemAuctionMap() {
        return itemAuctionMap;
    }

    @Override
    public Auction createAuction(Item item, double reservePrice) throws AuctionAlreadyExistException {
        if(itemAuctionMap.get(item) != null){
            throw new AuctionAlreadyExistException("An auction already exists for that item");
        }
        Auction auction =  new AuctionImpl(item, UUID.randomUUID(), reservePrice);
        itemAuctionMap.put(item, auction);
        return auction;
    }

}
