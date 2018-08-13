package app.Controller;

import app.service.AuctionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@WebMvcTest(AuctionController.class)
public class AuctionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuctionService auctionService;

    @Test
    public void getAuctions(){

    }






}