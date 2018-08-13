package app.service;

import app.exception.AuctionAlreadyExistException;
import app.data.model.Auction;
import app.data.model.AuctionImpl;
import app.data.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAuctionService {

    Auction createAuction(Item item, double reservePrice) throws AuctionAlreadyExistException;

    Auction findAuctionBydId(long id);

    List<AuctionImpl> getAllAuction();

    Auction findAuctionByItem(long itemId);

}
