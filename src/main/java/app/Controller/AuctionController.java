package app.Controller;

import app.Controller.request.AuctionRequest;
import app.data.model.Auction;
import app.data.model.Item;
import app.service.IAuctionService;
import app.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auctions")
public class AuctionController {

    private IAuctionService auctionService;


    private IItemService itemService;

    @Autowired
    public AuctionController(IAuctionService auctionService, IItemService itemService) {
        this.auctionService = auctionService;
        this.itemService = itemService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Auction> createAuction(@RequestBody AuctionRequest auctionRequest) {
        Item item = itemService.findCarById(auctionRequest.getItemId());
        return new ResponseEntity<>(auctionService.createAuction(item, auctionRequest.getReservePrice()), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Auction> getAllAuction() {
        return auctionService.getAllAuction();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Auction getAuction(@PathVariable long id) {
        return auctionService.findAuctionBydId(id);
    }


}
