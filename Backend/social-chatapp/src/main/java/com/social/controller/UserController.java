package com.social.controller;

import com.social.model.User;
import com.social.service.UserService;
import com.social.storage.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:8081/")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @PostMapping("/")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        User existingUser = userService.findByUserName(user.getUsername());
        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this username already exists");
        }
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        user.setConfirmPassword(this.bCryptPasswordEncoder.encode(user.getConfirmPassword()));

        User newUser = new User(user.getUsername(), user.getPassword(),user.getConfirmPassword(), user.getEmail());
        userService.saveUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }
    @GetMapping("/registration/{userName}")
    public ResponseEntity<Void> register(@PathVariable String userName) {
        System.out.println("handling register user request: " + userName);
        try {
            UserStorage.getInstance().setUser(userName);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/fetchAllUsers")
    public Set<String> fetchAll() {
        return UserStorage.getInstance().getUsers();
    }


}