package app.Controller;

import app.Controller.request.BidRequest;
import app.data.model.Auction;
import app.data.model.Bid;
import app.data.model.User;
import app.service.IAuctionService;
import app.service.IBidService;
import app.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bids")
public class BidController {

    @Autowired
    private IBidService bidService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IAuctionService auctionService;

    @PostMapping("")
    public Bid placeBid(@RequestBody BidRequest bidRequest){
        Auction auction = auctionService.findAuctionBydId(bidRequest.getAuctionId());
        User user = userService.findUserById(bidRequest.getUserId());
        Bid bid = new Bid(user, bidRequest.getAmount());
        return bidService.createBid(bid, auction);
    }

    @GetMapping("/{id}")
    public Bid getBidById(@PathVariable int id){
        return bidService.findBidById(id);
    }


    @GetMapping
    public List<Bid> findBidsForGivenItem(@RequestParam("itemId") long id){
        Auction auction = auctionService.findAuctionByItem(id) ;
        return auction.getBids();
    }

    @GetMapping("/winningBid")
    public Bid findWinningBidForGivenItem(@RequestParam("itemId") long id){
        Auction auction = auctionService.findAuctionByItem(id);
        return auction.getTopBid();
    }



}
