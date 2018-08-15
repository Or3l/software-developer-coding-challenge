package app.service;

import app.data.model.User;
import app.data.repository.UserRepository;
import app.exception.UserDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User findUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserDoesNotExistException("User does not exist"));

    }

    @Override
    public List<User> getAllUser() {
        Iterable<User> users = userRepository.findAll();
        List<User> result = new ArrayList<>();
        users.forEach(result::add);
        return result;
    }
}
