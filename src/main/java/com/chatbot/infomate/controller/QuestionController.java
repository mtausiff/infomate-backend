package com.chatbot.infomate.controller;

import com.chatbot.infomate.dto.CategoryDTO;
import com.chatbot.infomate.dto.QuestionDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.chatbot.infomate.model.Question;
import com.chatbot.infomate.service.ChatGptService;
import com.chatbot.infomate.service.QuestionService;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Question", description = "Question Controller")
@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private ChatGptService chatGptService;

    @Operation(summary = "Get questions by subcategory ID", description = "Returns a list of questions for a specific subcategory.")
    @GetMapping("/{subcategoryId}")
    public ResponseEntity<List<Question>> getQuestionsBySubcategoryId(
            @Parameter(description = "ID of the subcategory to retrieve questions for") @PathVariable Long subcategoryId) {
        List<Question> questions = questionService.getQuestionsBySubcategoryId(subcategoryId);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @Operation(summary = "Search questions", description = "Searches for questions based on a query string.")
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchQuestions(
            @Parameter(description = "Query string to search for questions") @RequestParam String query) {
        List<Question> results = questionService.searchQuestions(query);
        if (!results.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("questions", results);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            // Fetch response from ChatGPT via the ChatClient
            String chatGptResponse = chatGptService.getChatGptResponse(query);
            Map<String, Object> response = new HashMap<>();
            response.put("response", chatGptResponse);
            // Optionally add predefined video links or perform additional logic
            response.put("videos", List.of("YouTube_Link_1", "YouTube_Link_2"));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @Operation(summary = "Add new question", description = "Adds a new question and returns the created question details.")
    @PostMapping("/")
    public ResponseEntity addQuestion(
            @Parameter(description = "Question details to be added") @RequestBody QuestionDTO questionDTO) {
        questionService.addQuestion(questionDTO);
        return ResponseEntity.ok(null);
    }

    @Operation(summary = "Delete question", description = "Deletes a question by its ID.")
    @DeleteMapping("/{questionId}")
    public ResponseEntity deleteQuestionById(
            @Parameter(description = "ID of the question to be deleted") @PathVariable Long questionId) {
        questionService.deleteQuestion(questionId);
        return ResponseEntity.ok(null);
    }

    @Operation(summary = "Get all questions", description = "Returns a list of all questions.")
    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @Operation(summary = "Get question by ID", description = "Returns a question by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        return questionService.getQuestionById(id)
                .map(question -> new ResponseEntity<>(question, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Update question", description = "Updates an existing question.")
    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question question) {
        Question updatedQuestion = questionService.updateQuestion(id, question);
        return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
    }
}
