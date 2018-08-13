package app.Controller;

import app.data.model.User;
import app.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping
    public User addUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping(value = "/{id}")
    public User findUserById(@PathVariable long id){
        return userService.findUserById(id);
    }
}
