package app.Controller;

import app.data.model.Auction;
import app.data.model.Bid;
import app.data.model.Item;
import app.data.model.User;
import app.service.IAuctionService;
import app.service.IBidService;
import app.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(BidController.class)
public class BidControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IBidService bidService;

    @MockBean
    private IUserService userService;

    @MockBean
    private IAuctionService auctionService;

    private String expectedJson = "{\"bidId\":0,\"user\":{\"userId\":0,\"firstName\":\"user1\",\"lastName\":\"user1\"},\"amount\":10.0}";

    private List<Bid> mockBids = new ArrayList<>();
    private Bid mockBid = new Bid(new User("user1", "user1"), 10);


    @Test
    public void getAllBids() throws Exception {
        mockBids.add(mockBid);
        when(bidService.getAllBids()).thenReturn(mockBids);
        String expected = "[" + expectedJson + "]";
        mvc.perform(get("/bids")).andExpect(status().isOk()).andExpect(content().string(expected));
    }

    @Test
    public void createBid() throws Exception {

        String json = "{\n" +
                "\t\"userId\":1,\n" +
                "\t\"auctionId\":1,\n" +
                "\t\"amount\": 10\n" +
                "}";
        when(bidService.createBid(Mockito.any(Bid.class), Mockito.any(Auction.class))).thenReturn(mockBid);
        mvc.perform(post("/bids").content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    public void findBidsForItem() throws Exception {
        Auction auction = new Auction(new Item("car1", "car"), 0);
        mockBids.add(mockBid);
        auction.setBids(mockBids);
        when(auctionService.findAuctionByItem(1)).thenReturn(auction);
        String expected = "[" + expectedJson + "]";
        mvc.perform(get("/bids/item/1")).andExpect(status().isOk()).andExpect(content().string(expected));
    }

    @Test
    public void findWinningBidForItem() throws Exception {
        when(bidService.findWinningBidForItem(1)).thenReturn(mockBid);
        mvc.perform(get("/bids/winning/1")).andExpect(status().isOk()).andExpect(content().string(expectedJson));
    }
}