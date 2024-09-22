package com.chatbot.infomate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chatbot.infomate.repository.FeedbackRepository;
import com.chatbot.infomate.model.Feedback;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    public Feedback saveFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public List<Feedback> getFeedbackByQuestionId(Long questionId) {
        return feedbackRepository.findByQuestionId(questionId);
    }

    // Added CRUD methods
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    public Optional<Feedback> getFeedbackById(Long id) {
        return feedbackRepository.findById(id);
    }

    public Feedback updateFeedback(Long id, Feedback feedback) {
        if(feedbackRepository.findById(id).isEmpty()){
            throw new IllegalArgumentException("Feedback not found for given Id");
        }
        return feedbackRepository.save(feedback);
    }

    public void deleteFeedback(Long id) {
        if(feedbackRepository.findById(id).isEmpty()){
            throw new IllegalArgumentException("Feedback not found for given Id");
        }
        feedbackRepository.deleteById(id);
    }
}
