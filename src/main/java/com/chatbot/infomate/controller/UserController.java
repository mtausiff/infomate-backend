package com.chatbot.infomate.controller;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.chatbot.infomate.model.User;
import com.chatbot.infomate.service.UserService;


@Tag(name = "User", description = "User Controller")
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "get User details by accountId & role", description = "returns User details by accountId & role.")
    @PostMapping("/register")
    public ResponseEntity<User> doRegister(@Parameter(name = "type", description = "type of User", example = "registered,verifier,admin") @RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @Operation(summary = "get User details by accountId & role", description = "returns User details by accountId & role.")
    @PostMapping("/login")
    public ResponseEntity<User> doLogin(@Parameter(name = "type", description = "type of User", example = "registered,verifier,admin") @RequestBody User user) {
        User registeredUser = userService.loginUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @Operation(summary = "get User details by accountId & role", description = "returns User details by accountId & role.")
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@Parameter(name = "type", description = "type of User", example = "registered,verifier,admin") @PathVariable String username) {
        System.out.println("username = " + username);
        User user = userService.findByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
