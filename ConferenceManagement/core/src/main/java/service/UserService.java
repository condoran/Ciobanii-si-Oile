package service;

import domain.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    void save(User user);
}
