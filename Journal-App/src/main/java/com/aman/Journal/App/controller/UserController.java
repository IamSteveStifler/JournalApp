package com.aman.Journal.App.controller;

import com.aman.Journal.App.entity.User;
import com.aman.Journal.App.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/save")
    public ResponseEntity<Boolean> saveUser(@RequestBody User user){
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUser() {
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @GetMapping("/{userName}")
    public ResponseEntity<User> getUserByName(@PathVariable String userName) {
        return new ResponseEntity<>(userService.getUserByName(userName), HttpStatus.OK);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<Boolean> updateUserByUserName(@PathVariable String userName,
                                                     @RequestBody User user) {
        return new ResponseEntity<>(userService.updateUserByUserName(userName, user), HttpStatus.OK);
    }

    @DeleteMapping("/{userName}")
    public ResponseEntity<Boolean> deleteUserByUserName(@PathVariable String userName) {
        return new ResponseEntity<>(userService.deleteUserByUserName(userName), HttpStatus.OK);
    }

}
