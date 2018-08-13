package app.service;

import app.data.model.User;

public interface IUserService {
    User saveUser(User user);
    User findUserById(long id);
}
