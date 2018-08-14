package app.Controller;

import app.data.model.Auction;
import app.data.model.Item;
import app.service.IAuctionService;
import app.service.IItemService;
import org.junit.Before;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(AuctionController.class)
public class AuctionControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private IAuctionService auctionService;

    @MockBean
    private IItemService itemService;

    private List<Auction> mockAuctions;

    private String expectedJson = "{\"auctionId\":0,\"item\":{\"id\":0,\"name\":\"car1\",\"description\":\"super car\"},\"topBid\":null,\"reservePrice\":0.0}";
    private Auction expectedAuction = new Auction(new Item("car1", "super car"), 0);

    @Before
    public void setUp() {
        mockAuctions = new ArrayList<>();
    }

    @Test
    public void getAllAuctions() throws Exception {
        Auction auction = new Auction(new Item("car1", "super car"), 0);
        mockAuctions.add(auction);
        Mockito.when(auctionService.getAllAuction()).thenReturn(mockAuctions);
        String expect = "[" + expectedJson + "]";
        mvc.perform(get("/auctions")).andExpect(status().isOk()).andExpect(content().string(expect));
    }

    @Test
    public void findAuctionById() throws Exception {
        Auction auction = new Auction(new Item("car1", "super car"), 0);
        Mockito.when(auctionService.findAuctionBydId(1)).thenReturn(auction);
        mvc.perform(get("/auctions/1")).andExpect(status().isOk()).andExpect(content().string(expectedJson));
    }

    @Test
    public void createAuction() throws Exception {
        String json = "{\"item\":{\"id\":0,\"name\":\"car1\",\"description\":\"super car\"},\"topBid\":null,\"reservePrice\":0.0}";
        Mockito.when(auctionService.createAuction(Mockito.any(Item.class), Mockito.anyDouble())).thenReturn(expectedAuction);
        mvc.perform(post("/auctions").content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }


}