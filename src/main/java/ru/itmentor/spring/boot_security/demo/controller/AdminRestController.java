package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UsersServiceImpl;
import ru.itmentor.spring.boot_security.demo.util.ErrorResponse;
import ru.itmentor.spring.boot_security.demo.util.UserNotFoundException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    private final UsersServiceImpl usersServiceImpl;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminRestController(UsersServiceImpl usersServiceImpl, PasswordEncoder passwordEncoder) {
        this.usersServiceImpl = usersServiceImpl;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/users")
    public List<User> getUsers() {
        return usersServiceImpl.getAllUsers();
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id) {
        usersServiceImpl.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        usersServiceImpl.addUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<HttpStatus> updateUser(@PathVariable("id") int id, @RequestBody User updatedUser, BindingResult bindingResult) {
        User existingUser = usersServiceImpl.getUserById(id);
        if (existingUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        updatedUser.setId(id);
        usersServiceImpl.editUser(id, updatedUser);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(UserNotFoundException e) {
        ErrorResponse response = new ErrorResponse(
                "User with this id wasn't found!",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


}
