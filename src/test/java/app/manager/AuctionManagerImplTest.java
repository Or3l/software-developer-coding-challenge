package app.manager;

import app.exception.AuctionAlreadyExistException;
import app.exception.AuctionDoesNotExistException;
import app.exception.InsufficientAmountException;
import app.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AuctionManagerImplTest {

    private AuctionManager auctionManager;
    private User user1;
    private User user2;
    private Item item;
    private Auction auction;


    @BeforeEach
    public  void setUp() throws AuctionAlreadyExistException {
        auctionManager = new AuctionManagerImpl();
        user1 = new User("1", "test1", "test1");
        user2 = new User("2", "test2", "test2");
        item = new Car("model1", "Super car");
        auction = auctionManager.createAuction(item, 20);
    }


    @Test
    public void test_createAuction() throws AuctionAlreadyExistException {
        Item item = new Car("model2", "Benz");
        assertNotNull(auctionManager.createAuction(item, 20));
    }

    @Test
    public void test_createAuction_whenAuctionAlreadyExistForGivenItem() throws AuctionAlreadyExistException {
        Item item = new Car("model2", "Benz");
        Auction auction = auctionManager.createAuction(item, 20);
        assertThrows(AuctionAlreadyExistException.class, ()-> auctionManager.createAuction(item, 30));
    }

    @Test
    public void test_recordUserBid_whenBidInferiorReservePrice() throws InsufficientAmountException {
        Bid bid= new Bid(user1, 10);
        assertThrows(InsufficientAmountException.class, ()->auctionManager.recordUserBid(item, bid));
    }

    @Test
    public void test_recordUserBid_whenBidSuperiorReservePrice() throws InsufficientAmountException, AuctionDoesNotExistException {
        Bid bid= new Bid(user1, 238);
        auctionManager.recordUserBid(item, bid);
        assertEquals(1, auctionManager.getAllBids(item).size());
        assertEquals(user1, bid.getUser());
        assertEquals(bid, auctionManager.getTopBid(item));
    }

    @Test
    public void test_recordUserBid_whenAmountIsTheSameAsTopBid() throws InsufficientAmountException, AuctionDoesNotExistException {
        Bid bid= new Bid(user1, 238);
        auctionManager.recordUserBid(item, bid);
        assertThrows(InsufficientAmountException.class, ()-> auctionManager.recordUserBid(item, bid));
        assertEquals(1, auctionManager.getAllBids(item).size());
        assertEquals(user1, bid.getUser());
        assertEquals(bid, auctionManager.getTopBid(item));
    }

    @Test
    public void test_recordUserBid_whenAmountIsLowerThanTopBid() throws InsufficientAmountException, AuctionDoesNotExistException {
        Bid bid= new Bid(user1, 238);
        Bid low= new Bid(user1, 200);
        auctionManager.recordUserBid(item, bid);
        assertThrows(InsufficientAmountException.class, ()-> auctionManager.recordUserBid(item, low));
    }


    @Test
    public void test_getTopBid() throws InsufficientAmountException, AuctionDoesNotExistException {
        Bid bid= new Bid(user1, 239);
        Bid bid2 = new Bid(user2, 240);
        auctionManager.recordUserBid(item, bid);
        auctionManager.recordUserBid(item, bid2);
        assertEquals(2, auctionManager.getAllBids(item).size());
        assertEquals(bid2, auctionManager.getTopBid(item));
    }

    @Test
    public void test_getBidsForGivenItem() throws InsufficientAmountException, AuctionDoesNotExistException {
        Bid bid= new Bid(user1, 239);
        Bid bid2 = new Bid(user2, 240);
        Bid bid3 = new Bid(user2, 241);
        Set<Bid> expectedResult = new HashSet(Arrays.asList(bid, bid2, bid3));
        auctionManager.recordUserBid(item, bid);
        auctionManager.recordUserBid(item, bid2);
        auctionManager.recordUserBid(item, bid3);
        assertEquals(3, auctionManager.getAllBids(item).size());
        Set<Bid> actualResult = auctionManager.getAllBids(item);
        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void test_getBidsForGivenItem_false() throws InsufficientAmountException, AuctionDoesNotExistException {
        Bid bid = new Bid(user1, 239);
        Bid bid2 = new Bid(user2, 240);
        Bid bid3 = new Bid(user2, 241);
        Bid bid4 = new Bid(user2, 242);
        auctionManager.recordUserBid(item, bid);
        auctionManager.recordUserBid(item, bid2);
        auctionManager.recordUserBid(item, bid3);
        assertEquals(3, auctionManager.getAllBids(item).size());
        Set<Bid> allBids = auctionManager.getAllBids(item);
        assertFalse(allBids.contains(bid4));
    }


}