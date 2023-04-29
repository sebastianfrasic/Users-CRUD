package com.example.eafit.controllers;

import com.example.eafit.model.User;
import com.example.eafit.model.exceptions.BusinessException;
import com.example.eafit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.createUser(user), HttpStatus.ACCEPTED);
        } catch (BusinessException e) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getUserByID(@PathVariable String id) {
        try {
            return new ResponseEntity<>(userService.getUserByID(id), HttpStatus.ACCEPTED);
        } catch (BusinessException e) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("username/{name}")
    public ResponseEntity<?> getUserByName(@PathVariable String name) {
        try {
            return new ResponseEntity<>(userService.getUsersByName(name), HttpStatus.ACCEPTED);
        } catch (BusinessException e) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        try {
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.ACCEPTED);
        } catch (BusinessException e) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
