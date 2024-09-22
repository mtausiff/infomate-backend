package com.chatbot.infomate.service;

import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;

import java.util.List;

@Service
public class ChatGptService {
    private static final String SYSTEM_INSTRUCTIONS = "You are a helpful assistant that can answer questions and help with tasks.";

    private ChatClient chatClient;


    public ChatGptService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String getChatGptResponse(String query) {
        /*
         * PromptTemplate promptTemplate = new PromptTemplate(query);
         * Prompt prompt = promptTemplate.create();
         * ChatResponse chatResponse = chatClient.prompt(prompt).call().chatResponse();
         * return chatResponse.getResult().getOutput().getContent();
         */

        Prompt prompt = new Prompt( List.of(getSystemMessage(), getUserMessage(query)));
        ChatResponse response = chatClient.prompt(prompt).call().chatResponse();

        return response.getResults().get(0).getOutput().getContent();
    }

    private UserMessage getUserMessage(String query) {
        return new UserMessage(query);
    }

    private static SystemMessage getSystemMessage() {
        return new SystemMessage(SYSTEM_INSTRUCTIONS);
    }
}
