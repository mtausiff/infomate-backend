package com.chatbot.infomate.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.chatbot.infomate.model.User;
import com.chatbot.infomate.service.UserService;

import java.util.List;

@Tag(name = "User", description = "User Controller")
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Register a new user", description = "Registers a new user and returns the created user details.")
    @PostMapping("/register")
    public ResponseEntity<User> doRegister(
            @Parameter(description = "User details for registration") @RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @Operation(summary = "Login user", description = "Authenticates a user and returns the user details.")
    @PostMapping("/login")
    public ResponseEntity<User> doLogin(
            @Parameter(description = "User credentials for login") @RequestBody User user) {
        User registeredUser = userService.loginUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @Operation(summary = "Get user by username", description = "Returns user details for the given username.")
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(
            @Parameter(description = "Username of the user to retrieve") @PathVariable String username) {
        System.out.println("username = " + username);
        User user = userService.findByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(summary = "Get all users", description = "Returns a list of all users.")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(summary = "Get user by ID", description = "Returns a user by their ID.")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Update user", description = "Updates an existing user.")
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @Operation(summary = "Delete user", description = "Deletes a user by their ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
