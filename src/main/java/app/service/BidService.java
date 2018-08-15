package app.service;

import app.data.model.Auction;
import app.data.model.Bid;
import app.data.repository.BidRepository;
import app.exception.BidDoesNotExistException;
import app.exception.InsufficientAmountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BidService implements IBidService {

    private BidRepository bidRepository;

    private IAuctionService auctionService;

    @Autowired
    public BidService(BidRepository bidRepository, IAuctionService auctionService) {
        this.bidRepository = bidRepository;
        this.auctionService = auctionService;
    }

    @Override
    public Bid createBid(Bid bid, Auction auction) {
        if (Double.compare(bid.getAmount(), auction.getReservePrice()) < 0) {
            throw new InsufficientAmountException("The amount is lesser than the reserve price");
        }
        if (auction.getTopBid() == null) {
            auction.setTopBid(bid);
            bid.setAuction(auction);
        } else {
            if (Double.compare(bid.getAmount(), auction.getTopBid().getAmount()) <= 0) {
                throw new InsufficientAmountException("The amount is lesser than the top bid");
            } else {
                bid.setAuction(auction);
                auction.setTopBid(bid);
            }
        }
        return bidRepository.save(bid);
    }

    @Override
    public Bid findBidById(long id) {
        return bidRepository.findById(id).orElseThrow(() -> new BidDoesNotExistException("This bid does not exist"));
    }

    @Override
    public List<Bid> getAllBids() {
        Iterable<Bid> auctions = bidRepository.findAll();
        List<Bid> result = new ArrayList<>();
        auctions.forEach(result::add);
        return result;
    }

    @Override
    public Bid findWinningBidForItem(long id) {
        Auction auction = auctionService.findAuctionByItem(id);
        return auction.getTopBid();
    }
}
