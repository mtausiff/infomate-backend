package com.chatbot.infomate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chatbot.infomate.repository.FeedbackRepository;
import com.chatbot.infomate.model.Feedback;
import java.util.List;

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
}
