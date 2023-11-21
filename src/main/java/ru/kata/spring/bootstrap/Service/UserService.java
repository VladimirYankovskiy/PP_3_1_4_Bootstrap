package ru.kata.spring.bootstrap.Service;

import ru.kata.spring.bootstrap.models.User;

import java.util.List;

public interface UserService {
    List<User> allUsers();

    User showUser(Long id);

    void saveUser(User user);

    void updateUser(User user);

    void delUser(Long id);

    User findByUsername(String name);
}
