package app.Controller;

import app.data.model.Car;
import app.data.model.Item;
import app.service.IItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
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

    private String expectedJson = "{\"id\":0,\"name\":\"car1\",\"description\":\"super car\"}";

    private List<Item> mockItems = new ArrayList<>();

    @Test
    public void getAll() throws Exception {
        mockItems.add(mockCar);
        when(itemService.getAllItem()).thenReturn(mockItems);
        String expected = "["+expectedJson+"]";
        mvc.perform(get("/cars")).andExpect(status().isOk()).andExpect(content().string(expected));
    }

    @Test
    public void findItemBydId_whenItemExist() throws Exception {
        when(itemService.findCarById(1)).thenReturn(mockCar);
        mvc.perform(get("/cars/1")).andExpect(status().isOk()).andExpect(content().json(expectedJson));
    }

    @Test
    public void findItemBydId_whenItemDoesNotExist() throws Exception {
        mvc.perform(get("/cars/1")).andExpect(status().isOk());
    }

    @Test
    public void createCar() throws Exception {
        when(itemService.saveCar(Mockito.any(Car.class))).thenReturn(mockCar);
        mvc.perform(post("/cars").content("{\"name\":\"car1\",\"description\":\"super car\"}").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }


}