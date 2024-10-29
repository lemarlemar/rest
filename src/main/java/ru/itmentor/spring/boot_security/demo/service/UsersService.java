package ru.itmentor.spring.boot_security.demo.service;

import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

public interface UsersService {

    public User getUserById(int id);


    public void addUser(User user);


    public void editUser(int id, User updatedUser);

    public void deleteUser(int id);

    public List<User> getAllUsers();

    User findByUsername(String name);

}
