package app.Controller;

import app.data.model.User;
import app.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IUserService userService;

    @Test
    public void findUserById_whenUserExist() throws Exception {
        User user = new User("test1", "test1");
        when(userService.findUserById(1)).thenReturn(user);
        this.mvc.perform(get("/users/1")).andExpect(status().isOk()).andExpect(content().json("{\"userId\":0,\"firstName\":\"test1\",\"lastName\":\"test1\"}"));

    }



}