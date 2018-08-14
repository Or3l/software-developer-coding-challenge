package app.Controller;

import app.data.model.Car;
import app.data.model.Item;
import app.service.IItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IItemService itemService;

    private Car mockCar = new Car("car1", "super car");

    @Test
    public void findItemBydId_whenItemExist() throws Exception {
        Item item = new Item("car1", "super car");
        BDDMockito.when(itemService.findCarById(1)).thenReturn(item);
        mvc.perform(get("/cars/1")).andExpect(status().isOk()).andExpect(content().json("{\"id\":0,\"name\":\"car1\",\"description\":\"super car\"}"));
    }

    @Test
    public void findItemBydId_whenItemDoesNotExist() throws Exception {
        mvc.perform(get("/cars/1")).andExpect(status().isOk());
    }

    @Test
    public void createCar() throws Exception {
        BDDMockito.when(itemService.saveCar(Mockito.any(Car.class))).thenReturn(mockCar);
        mvc.perform(post("/cars").content("{\"name\":\"car1\",\"description\":\"super car\"}").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }


}