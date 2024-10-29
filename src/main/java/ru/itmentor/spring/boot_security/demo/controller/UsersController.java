package ru.itmentor.spring.boot_security.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UsersServiceImpl;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UsersServiceImpl usersServiceImpl;

    @Autowired
    public UsersController(UsersServiceImpl usersServiceImpl) {
        this.usersServiceImpl = usersServiceImpl;
    }

    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("users", usersServiceImpl.getAllUsers());
        return "/admin/users";
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
        return "/admin/edit_user";
    }

    @PostMapping("edit/{id}")
    public String updateUser(@PathVariable("id") int id, @ModelAttribute User updatedUser, String name, Model model) {
        usersServiceImpl.getUserById(id);
        updatedUser.setName(name);
        usersServiceImpl.editUser(id, updatedUser);
        model.addAttribute("user", usersServiceImpl.getUserById(id));
        return "redirect:/admin/users";
    }

    @PostMapping(value = "/add")
    public String createUser(@ModelAttribute User user) {
        usersServiceImpl.addUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("userinfo/{id}")
    public String userInfo(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", usersServiceImpl.getUserById(id));
        return "/user/userinfo";
    }
}




