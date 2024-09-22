package com.chatbot.infomate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chatbot.infomate.model.User;
import com.chatbot.infomate.repository.UserRepository;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public User loginUser(User user) {
        // login logic here
        return user;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(Long id, User user) {
        if(userRepository.findById(id).isEmpty()){
            throw new IllegalArgumentException("User not found for given Id");
        }
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if(userRepository.findById(id).isEmpty()){
            throw new IllegalArgumentException("User not found for given Id");
        }
        userRepository.deleteById(id);
    }
}
