package ru.itmentor.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.UserDao.UserDao;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

    private UserDao userDao;

    @Autowired
    public UsersServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Transactional
    public void addUser(User user) {
        userDao.createUser(user);
    }

    @Transactional
    public void editUser(int id, User updatedUser) {
        updatedUser.setId(id);
        userDao.updateUser(updatedUser);
    }

    @Transactional
    public void deleteUser(int id) {
        userDao.deleteUserById(id);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User findByUsername(String name){
        return userDao.findByUsername(name);
    }

}
