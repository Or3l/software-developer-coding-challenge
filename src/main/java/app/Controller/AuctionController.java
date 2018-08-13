package app.Controller;

import app.Controller.request.AuctionRequest;
import app.data.model.Auction;
import app.data.model.AuctionImpl;
import app.data.model.Item;
import app.service.IAuctionService;
import app.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auctions")
public class AuctionController {

    @Autowired
    private IAuctionService auctionService;

    @Autowired
    private IItemService carService;

    @PostMapping
    public Auction createAuction(@RequestBody AuctionRequest auctionRequest) {
        Item item = carService.findCarById(auctionRequest.getItemId());
        return auctionService.createAuction(item, auctionRequest.getReservePrice());
    }

    @GetMapping
    public List<AuctionImpl> getAllAuction(){
        return auctionService.getAllAuction();
    }



}
