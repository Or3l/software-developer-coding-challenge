//package app.data.model;
//
//import app.exception.AuctionAlreadyExistException;
//import app.exception.AuctionDoesNotExistException;
//import app.exception.InsufficientAmountException;
//import app.manager.AuctionManager;
//import app.manager.AuctionManagerImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class AuctionImplTest {
//    private Auction auction;
//
//    private User user;
//
//    private AuctionManager auctionManager;
//
//    private Item item;
//
//    @BeforeEach
//    public void setUp() throws AuctionAlreadyExistException {
//        auctionManager = new AuctionManagerImpl();
//        item = new Car("test", "test");
//        auction = auctionManager.createAuction(item, 1);
//        user = new User("hello", "test");
//    }
//
//
//
//    @Test
//    public void test_hasBids_true() throws InsufficientAmountException, AuctionDoesNotExistException {
//        Bid bid = new Bid(user, 1);
//        auctionManager.recordUserBid(item, bid);
//        assertTrue(auctionManager
//                .getItemAuctionMap()
//                .get(item).hasBids());
//
//    }
//
//}