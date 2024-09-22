package com.chatbot.infomate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.chatbot.infomate.model.Question;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    String MOST_ASKED_QUESTIONS_QUERY = "SELECT q FROM Question q LEFT JOIN Feedback f ON f.question = q GROUP BY q ORDER BY COUNT(f.id) DESC";

    List<Question> findBySubcategoryId(Long subcategoryId);

    List<Question> findAllByQuestionTextContainingIgnoreCase(String query);

    Optional<Question> findByQuestionTextContainingIgnoreCase(String query);

    List<Question> findByQuestionTextContainingIgnoreCaseAndSubcategoryId(String query, Long subcategoryId);

    @Query(MOST_ASKED_QUESTIONS_QUERY)
    List<Question> findMostAskedQuestions();
}
