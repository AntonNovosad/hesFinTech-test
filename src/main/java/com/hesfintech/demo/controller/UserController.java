package com.hesfintech.demo.controller;


import com.hesfintech.demo.model.Token;
import com.hesfintech.demo.model.User;
import com.hesfintech.demo.service.TokenService;
import com.hesfintech.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody @Valid User user) {
        if (userService.save(user)) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/login")
    public ResponseEntity<Token> login(String username, String password) {
        if (userService.login(username, password)) {
            Token token = tokenService.generate(username);
            return new ResponseEntity<>(token, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{username}/edit")
    public void update(@PathVariable String username, @RequestBody User user) {
        userService.update(username, user);
    }


    @GetMapping("/{username}")
    public ResponseEntity<User> get(@PathVariable String username) {
        User byUsername = userService.getByUsername(username);
        if (byUsername == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(byUsername, HttpStatus.ACCEPTED);
    }

    @GetMapping("/findByRole")
    public ResponseEntity<List<User>> getByRole(@RequestBody String[] role) {
        List<User> users = userService.listByRole(role);
        return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
    }
}
