package ru.itmentor.spring.boot_security.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UsersServiceImpl;

import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UsersServiceImpl usersServiceImpl;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UsersServiceImpl usersServiceImpl, PasswordEncoder passwordEncoder) {
        this.usersServiceImpl = usersServiceImpl;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("users")
    public String getUsers(Model model) {
        model.addAttribute("users", usersServiceImpl.getAllUsers());
        return "users";
    }

    @GetMapping("/add")
    public String addUser() {
        return "add";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        usersServiceImpl.deleteUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", usersServiceImpl.getUserById(id));
        return "edit_user";
    }

    @PostMapping("edit/{id}")
    public String updateUser(@PathVariable("id") int id, @ModelAttribute User updatedUser, String name, Model model) {
        usersServiceImpl.getUserById(id);
        updatedUser.setName(name);
        usersServiceImpl.editUser(id, updatedUser);
        model.addAttribute("user", usersServiceImpl.getUserById(id));
        return "redirect:/admin/users";
    }

    @PostMapping( "add")
    public String createUser(@ModelAttribute User user) {
        usersServiceImpl.addUser(user);
        return "redirect:/admin/users";
    }


}




