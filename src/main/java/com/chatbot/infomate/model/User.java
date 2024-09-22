package com.chatbot.infomate.model;

import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private RoleEnum userType; // "GUEST", "REGISTERED"

    public User() {
    }

    public User(String username, String password, RoleEnum userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleEnum getUserType() {
        return userType;
    }

    public void setUserType(RoleEnum userType) {
        this.userType = userType;
    }
}
