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

import com.chatbot.infomate.model.Feedback;
import com.chatbot.infomate.service.FeedbackService;

import java.util.List;


@Tag(name = "Feedback", description = "Feedback Controller")
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @Operation(summary = "get User details by accountId & role", description = "returns User details by accountId & role.")
    @PostMapping
    public ResponseEntity<Feedback> saveFeedback(@Parameter(name = "type", description = "type of User", example = "registered,verifier,admin") @RequestBody Feedback feedback) {
        Feedback savedFeedback = feedbackService.saveFeedback(feedback);
        return new ResponseEntity<>(savedFeedback, HttpStatus.CREATED);
    }

    @Operation(summary = "get User details by accountId & role", description = "returns User details by accountId & role.")
    @GetMapping("/{questionId}")
    public ResponseEntity<List<Feedback>> getFeedbackByQuestionId(@Parameter(name = "type", description = "type of User", example = "registered,verifier,admin") @PathVariable Long questionId) {
        List<Feedback> feedbacks = feedbackService.getFeedbackByQuestionId(questionId);
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }
}
