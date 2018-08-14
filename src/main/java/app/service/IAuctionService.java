package app.service;

import app.data.model.Auction;
import app.data.model.Item;

import java.util.List;

public interface IAuctionService {

    Auction createAuction(Item item, double reservePrice);

    Auction findAuctionBydId(long id);

    List<Auction> getAllAuction();

    Auction findAuctionByItem(long itemId);

}
