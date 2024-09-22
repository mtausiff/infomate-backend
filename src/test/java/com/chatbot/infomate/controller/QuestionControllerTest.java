package com.chatbot.infomate.controller;

import com.chatbot.infomate.dto.QuestionDTO;
import com.chatbot.infomate.model.Question;
import com.chatbot.infomate.model.Subcategory;
import com.chatbot.infomate.service.ChatGptService;
import com.chatbot.infomate.service.QuestionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuestionController.class)
public class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionService questionService;

    @MockBean
    private ChatGptService chatGptService;

    @Autowired
    private ObjectMapper objectMapper;

    private Question sampleQuestion;
    private Subcategory sampleSubcategory;

    @BeforeEach
    void setUp() {
        sampleSubcategory = new Subcategory("Test Subcategory", null);
        sampleQuestion = new Question("Test question?", "Test answer", sampleSubcategory, 0);
    }

    @Test
    void testGetAllQuestions() throws Exception {

        // The findAll method is automatically implemented by Spring Data JPA
        when(questionService.getAllQuestions()).thenReturn(Arrays.asList(sampleQuestion));

        mockMvc.perform(get("/api/questions"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].questionText").value("Test question?"));

        verify(questionService).getAllQuestions();
    }

    @Test
    void testGetQuestionById() throws Exception {
        when(questionService.getQuestionById(1L)).thenReturn(Optional.of(sampleQuestion));

        mockMvc.perform(get("/api/questions/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.questionText").value("Test question?"));

        verify(questionService).getQuestionById(1L);
    }

    @Test
    void testGetQuestionByIdNotFound() throws Exception {
        when(questionService.getQuestionById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/questions/1"))
                .andExpect(status().isNotFound());

        verify(questionService).getQuestionById(1L);
    }

    @Test
    void testCreateQuestion() throws Exception {
        when(questionService.saveQuestion(any(Question.class))).thenReturn(sampleQuestion);

        mockMvc.perform(post("/api/questions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleQuestion)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.questionText").value("Test question?"));

        verify(questionService).saveQuestion(any(Question.class));
    }

    @Test
    void testUpdateQuestion() throws Exception {
        when(questionService.getQuestionById(1L)).thenReturn(Optional.of(sampleQuestion));
        when(questionService.saveQuestion(any(Question.class))).thenReturn(sampleQuestion);

        mockMvc.perform(put("/api/questions/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleQuestion)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.questionText").value("Test question?"));

        verify(questionService).getQuestionById(1L);
        verify(questionService).saveQuestion(any(Question.class));
    }

    @Test
    void testUpdateQuestionNotFound() throws Exception {
        when(questionService.getQuestionById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/questions/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleQuestion)))
                .andExpect(status().isNotFound());

        verify(questionService).getQuestionById(1L);
        verify(questionService, never()).saveQuestion(any(Question.class));
    }

    @Test
    void testDeleteQuestion() throws Exception {
        when(questionService.getQuestionById(1L)).thenReturn(Optional.of(sampleQuestion));
        doNothing().when(questionService).deleteQuestion(1L);

        mockMvc.perform(delete("/api/questions/1"))
                .andExpect(status().isNoContent());

        verify(questionService).getQuestionById(1L);
        verify(questionService).deleteQuestion(1L);
    }

    @Test
    void testDeleteQuestionNotFound() throws Exception {
        when(questionService.getQuestionById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/questions/1"))
                .andExpect(status().isNotFound());

        verify(questionService).getQuestionById(1L);
        verify(questionService, never()).deleteQuestion(anyLong());
    }

    @Test
    void testGetQuestionsBySubcategoryIdWay1() throws Exception {
        when(questionService.getQuestionsBySubcategoryId(1L)).thenReturn(Arrays.asList(sampleQuestion));

        mockMvc.perform(get("/api/questions/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].questionText").value("Test question?"));

        verify(questionService).getQuestionsBySubcategoryId(1L);
    }

    @Test
    void testGetQuestionsBySubcategoryIdWay2() throws Exception {
        when(questionService.getQuestionsBySubcategoryId(1L)).thenReturn(Arrays.asList(sampleQuestion));

        mockMvc.perform(get("/api/questions/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").exists());

        verify(questionService).getQuestionsBySubcategoryId(1L);
    }

    @Test
    void testGetQuestionsBySubcategoryIdWay3() throws Exception {
        when(questionService.getQuestionsBySubcategoryId(1L)).thenReturn(Arrays.asList(sampleQuestion));

        String response = mockMvc.perform(get("/api/questions/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        System.out.println("Response: " + response);

        // Add your assertions here based on the actual response structure

        verify(questionService).getQuestionsBySubcategoryId(1L);
    }

    @Test
    void testSearchQuestionsWithResults() throws Exception {
        when(questionService.searchQuestions("test")).thenReturn(Arrays.asList(sampleQuestion));

        mockMvc.perform(get("/api/questions/search").param("query", "test"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.questions[0].questionText").value("Test question?"));

        verify(questionService).searchQuestions("test");
    }

    @Test
    void testSearchQuestionsWithoutResults() throws Exception {
        when(questionService.searchQuestions("test")).thenReturn(List.of());
        when(chatGptService.getChatGptResponse("test")).thenReturn("ChatGPT response");

        mockMvc.perform(get("/api/questions/search").param("query", "test"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.response").value("ChatGPT response"))
                .andExpect(jsonPath("$.videos").isArray());

        verify(questionService).searchQuestions("test");
        verify(chatGptService).getChatGptResponse("test");
    }

    @Test
    void testAddQuestion() throws Exception {
        QuestionDTO questionDTO = new QuestionDTO(); // Assume you have a QuestionDTO class
        doNothing().when(questionService).addQuestion(any(QuestionDTO.class));

        mockMvc.perform(post("/api/questions/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(questionDTO)))
                .andExpect(status().isOk());

        verify(questionService).addQuestion(any(QuestionDTO.class));
    }

    @Test
    void testDeleteQuestionById() throws Exception {
        doNothing().when(questionService).deleteQuestion(1L);

        mockMvc.perform(delete("/api/questions/1"))
                .andExpect(status().isOk());

        verify(questionService).deleteQuestion(1L);
    }
}
