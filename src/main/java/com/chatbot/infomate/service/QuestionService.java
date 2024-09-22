package com.chatbot.infomate.service;

import java.util.List;
import java.util.Optional;

import com.chatbot.infomate.dto.QuestionDTO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.chatbot.infomate.repository.QuestionRepository;
import com.chatbot.infomate.model.Question;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getQuestionsBySubcategoryId(Long subcategoryId) {
        return questionRepository.findBySubcategoryId(subcategoryId);
    }

    public List<Question> searchQuestions(String query) {
        return questionRepository.findAllByQuestionTextContainingIgnoreCase(query);
    }

    public Optional<Question> searchQuestion(String query) {
        return questionRepository.findByQuestionTextContainingIgnoreCase(query);
    }

    public List<Question> advancedSearch(String query, Long subcategoryId) {
        return questionRepository.findByQuestionTextContainingIgnoreCaseAndSubcategoryId(query, subcategoryId);
    }

    public void addQuestion(QuestionDTO questionDTO) {
        
    }

    public void deleteQuestion(Long questionId) {
    }

/*    public List<Question> getMostAskedQuestions() {
        // Check cache first
        List<Question> cachedQuestions = redisTemplate.opsForList().range("most_asked_questions", 0, -1);
        if (!cachedQuestions.isEmpty()) {
            return cachedQuestions;
        }

        // If not cached, fetch from DB and cache it
        List<Question> questions = questionRepository.findMostAskedQuestions();
        redisTemplate.opsForList().rightPushAll("most_asked_questions", questions);
        return questions;
    }*/
}
