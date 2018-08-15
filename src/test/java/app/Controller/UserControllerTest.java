package app.Controller;

import app.data.model.User;
import app.service.IUserService;
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
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IUserService userService;

    private User mockUser = new User("user1", "user1");

    private List<User> mockUsers = new ArrayList<>();

    private String expectedJson = "{\"userId\":0,\"firstName\":\"user1\",\"lastName\":\"user1\"}";


    @Test
    public void getAll() throws Exception {
        mockUsers.add(mockUser);
        Mockito.when(userService.getAllUser()).thenReturn(mockUsers);
        String expected = "["+expectedJson+"]";
        mvc.perform(get("/users")).andExpect(status().isOk()).andExpect(content().string(expected));

    }

    @Test
    public void findUserById_whenUserExist() throws Exception {
        when(userService.findUserById(1)).thenReturn(mockUser);
        this.mvc.perform(get("/users/1")).andExpect(status().isOk()).andExpect(content().json(expectedJson));
    }

    @Test
    public void createUser() throws Exception {
        when(userService.saveUser(Mockito.any(User.class))).thenReturn(mockUser);
        mvc.perform(post("/users").content(expectedJson).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }


}