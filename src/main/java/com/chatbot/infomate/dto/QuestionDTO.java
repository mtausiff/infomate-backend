package com.chatbot.infomate.dto;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QuestionDTO {
    private String query;
    private String answer;
    private List<String> refLinks;

}