package app.service;

import app.exception.UserDoesNotExistException;
import app.data.model.User;
import app.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public User findUserById(long id)  {
        return userRepository.findById(id).orElseThrow(()-> new UserDoesNotExistException("User does not exist"));

    }
}
