package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UsersServiceImpl;
@Controller
@RequestMapping("/user")
public class UsersController {
    private final UsersServiceImpl usersServiceImpl;

    @Autowired
    public UsersController(UsersServiceImpl usersServiceImpl) {
        this.usersServiceImpl = usersServiceImpl;
    }

    @GetMapping()
    public String userInfo(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = usersServiceImpl.findByUsername(userDetails.getUsername());
        model.addAttribute("user", user);
        return "userinfo";
    }


}
