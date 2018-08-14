package app.service;

import app.data.model.Auction;
import app.data.model.Item;
import app.data.repository.AuctionRepository;
import app.exception.AuctionAlreadyExistException;
import app.exception.AuctionDoesNotExistException;
import app.exception.BidDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuctionService implements IAuctionService {


    private AuctionRepository auctionRepository;

    @Autowired
    public AuctionService(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    @Override
    public Auction createAuction(Item item, double reservePrice) {
        if (item.getAuction() != null) {
            throw new AuctionAlreadyExistException("Auction already exist for that item");
        }
        Auction auction = new Auction(item, reservePrice);
        item.setAuction(auction);
        return auctionRepository.save(auction);
    }

    @Override
    public Auction findAuctionBydId(long id) {
        return auctionRepository.findById(id).orElseThrow(() -> new AuctionDoesNotExistException("The auction does not exist"));
    }

    @Override
    public List<Auction> getAllAuction() {
        Iterable<Auction> auctions = auctionRepository.findAll();
        List<Auction> result = new ArrayList<>();
        auctions.forEach(result::add);
        return result;
    }

    @Override
    public Auction findAuctionByItem(long itemId) {
        return auctionRepository.findByItemId(itemId).orElseThrow(() -> new BidDoesNotExistException("There are no bids for the given item"));
    }


}
