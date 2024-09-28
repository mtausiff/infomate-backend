package com.chatbot.infomate.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.chatbot.infomate.model.Feedback;
import com.chatbot.infomate.service.FeedbackService;

import java.util.List;

@Tag(name = "Feedback", description = "Feedback Controller")
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @Operation(summary = "Save feedback", description = "Saves new feedback and returns the created feedback details.")
    @PostMapping
    public ResponseEntity<Feedback> saveFeedback(
            @Parameter(description = "Feedback details to be saved") @RequestBody Feedback feedback) {
        Feedback savedFeedback = feedbackService.saveFeedback(feedback);
        return new ResponseEntity<>(savedFeedback, HttpStatus.CREATED);
    }

    @Operation(summary = "Get feedback by question ID", description = "Returns a list of feedback for a specific question.")
    @GetMapping("/{questionId}")
    public ResponseEntity<List<Feedback>> getFeedbackByQuestionId(
            @Parameter(description = "ID of the question to retrieve feedback for") @PathVariable Long questionId) {
        List<Feedback> feedbacks = feedbackService.getFeedbackByQuestionId(questionId);
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }

    @Operation(summary = "Get all feedback", description = "Returns a list of all feedback.")
    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedback() {
        List<Feedback> feedbacks = feedbackService.getAllFeedback();
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }

    @Operation(summary = "Get feedback by ID", description = "Returns a feedback by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long id) {
        return feedbackService.getFeedbackById(id)
                .map(feedback -> new ResponseEntity<>(feedback, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Update feedback", description = "Updates an existing feedback.")
    @PutMapping("/{id}")
    public ResponseEntity<Feedback> updateFeedback(@PathVariable Long id, @RequestBody Feedback feedback) {
        Feedback updatedFeedback = feedbackService.updateFeedback(id, feedback);
        return new ResponseEntity<>(updatedFeedback, HttpStatus.OK);
    }

    @Operation(summary = "Delete feedback", description = "Deletes a feedback by its ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
