package app.service;

import app.data.model.User;

import java.util.List;

public interface IUserService {
    User saveUser(User user);

    User findUserById(long id);

    List<User> getAllUser();
}
