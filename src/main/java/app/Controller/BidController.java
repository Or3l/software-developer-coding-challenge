package app.Controller;

import app.Controller.request.BidRequest;
import app.data.model.Auction;
import app.data.model.Bid;
import app.data.model.User;
import app.service.IAuctionService;
import app.service.IBidService;
import app.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bids")
public class BidController {

    private IBidService bidService;

    private IUserService userService;

    private IAuctionService auctionService;

    @Autowired
    public BidController(IBidService bidService, IUserService userService, IAuctionService auctionService) {
        this.bidService = bidService;
        this.userService = userService;
        this.auctionService = auctionService;
    }

    @PostMapping
    public ResponseEntity<Bid> placeBid(@RequestBody BidRequest bidRequest){
        Auction auction = auctionService.findAuctionBydId(bidRequest.getAuctionId());
        User user = userService.findUserById(bidRequest.getUserId());
        Bid bid = new Bid(user, bidRequest.getAmount());
        return new ResponseEntity<>(bidService.createBid(bid, auction), HttpStatus.CREATED);
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

    @GetMapping("/all")
    public List<Bid> getAllBids(){
        return bidService.getAllBids();
    }



}
