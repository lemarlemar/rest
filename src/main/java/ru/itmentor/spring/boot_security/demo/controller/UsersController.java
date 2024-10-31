package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itmentor.spring.boot_security.demo.service.UsersServiceImpl;
@Controller
@RequestMapping("/user")
public class UsersController {
    private final UsersServiceImpl usersServiceImpl;

    @Autowired
    public UsersController(UsersServiceImpl usersServiceImpl) {
        this.usersServiceImpl = usersServiceImpl;
    }

    @GetMapping("userinfo/{id}")
    public String userInfo(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", usersServiceImpl.getUserById(id));
        return "userinfo";
    }
}
