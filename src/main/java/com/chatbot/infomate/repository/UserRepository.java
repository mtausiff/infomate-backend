package com.chatbot.infomate.repository;

import com.chatbot.infomate.model.User; // Add this import
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findById(Long id);

    List<User> findAll();

    User save(User user);

    void deleteById(Long id);

    boolean existsById(Long id);
}
