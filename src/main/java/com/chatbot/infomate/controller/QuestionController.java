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

    @Operation(summary = "get User details by accountId & role", description = "returns User details by accountId & role.")
    @GetMapping("/{subcategoryId}")
    public ResponseEntity<List<Question>> getQuestionsBySubcategoryId(@Parameter(name = "type", description = "type of User", example = "registered,verifier,admin") @PathVariable Long subcategoryId) {
        List<Question> questions = questionService.getQuestionsBySubcategoryId(subcategoryId);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @Operation(summary = "get User details by accountId & role", description = "returns User details by accountId & role.")
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchQuestions(@Parameter(name = "type", description = "type of User", example = "registered,verifier,admin") @RequestParam String query) {
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

    @Operation(summary = "get Question details by accountId & role", description = "returns Question details by accountId & role.")
    @PostMapping("/")
    public ResponseEntity addQuestion(@Parameter(name = "type", description = "type of Question", example = "registered,verifier,admin") @PathVariable QuestionDTO questionDTO) {
        questionService.addQuestion(questionDTO);
        return ResponseEntity.ok(null);
    }

    @Operation(summary = "get Question details by accountId & role", description = "returns Question details by accountId & role.")
    @DeleteMapping("/{questionId}")
    public ResponseEntity deleteQuestionById(@Parameter(name = "type", description = "type of Question", example = "registered,verifier,admin") @PathVariable Long questionId) {
        questionService.deleteQuestion(questionId);
        return ResponseEntity.ok(null);
    }
}
