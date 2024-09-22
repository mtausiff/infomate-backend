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

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Optional<Question> getQuestionById(Long questionId) {
        return questionRepository.findById(questionId);
    }

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

    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question addQuestion(QuestionDTO questionDTO) {
        Question question = new Question(); // Convert DTO to entity
        // Set question properties from DTO
        return questionRepository.save(question);
    }

    public void deleteQuestion(Long questionId) {
        if(questionRepository.findById(questionId).isEmpty()){
            throw new IllegalArgumentException("Question not found for given Id");
        }
        questionRepository.deleteById(questionId);
    }

    public Question updateQuestion(Long id, Question question) {
        if(questionRepository.findById(id).isEmpty()){
            throw new IllegalArgumentException("Question not found for given Id");
        }
        return questionRepository.save(question);
    }
}
