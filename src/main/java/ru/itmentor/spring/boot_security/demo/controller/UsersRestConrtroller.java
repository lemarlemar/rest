package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UsersServiceImpl;

@RestController
@RequestMapping("/user")
public class UsersRestConrtroller {

    private final UsersServiceImpl usersServiceImpl;

    @Autowired
    public UsersRestConrtroller(UsersServiceImpl usersServiceImpl) {
        this.usersServiceImpl = usersServiceImpl;
    }

    @GetMapping("/{id}")
    public User userInfo(@PathVariable ("id") int id) {
        return usersServiceImpl.getUserById(id);
    }

}
