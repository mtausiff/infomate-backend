package com.chatbot.infomate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.chatbot.infomate.model.Feedback;
import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    Optional<Feedback> findById(Long id);

    List<Feedback> findAll();

    List<Feedback> findByQuestionId(Long questionId);

    Feedback save(Feedback feedback);

    void deleteById(Long id);

    boolean existsById(Long id);
}
