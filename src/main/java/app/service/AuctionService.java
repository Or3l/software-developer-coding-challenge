package app.service;

import app.exception.AuctionAlreadyExistException;
import app.exception.AuctionDoesNotExistException;
import app.data.model.Auction;
import app.data.model.AuctionImpl;
import app.data.model.Item;
import app.data.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuctionService implements IAuctionService {

    @Autowired
    private AuctionRepository auctionRepository;

    @Override
    public Auction createAuction(Item item, double reservePrice) throws AuctionAlreadyExistException {
        if(item.getAuction() != null){
            throw new AuctionAlreadyExistException("Auction already exist for that item");
        }
        AuctionImpl auction = new AuctionImpl(item, reservePrice);
        item.setAuction(auction);
        return auctionRepository.save(auction);
    }

    @Override
    public Auction findAuctionBydId(long id) {
        return auctionRepository.findById(id).orElseThrow(()-> new AuctionDoesNotExistException("The auction does not exist"));
    }

    @Override
    public List<AuctionImpl> getAllAuction() {
        Iterable<AuctionImpl> auctions = auctionRepository.findAll();
        List<AuctionImpl> result = new ArrayList<>();
        auctions.forEach(result::add);
        return result;
    }

    @Override
    public Auction findAuctionByItem(long itemId) {
        return auctionRepository.findByItemId(itemId);
    }


}
