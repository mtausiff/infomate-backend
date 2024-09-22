package com.chatbot.infomate.model;

import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "chat_messages")
@Slf4j
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String sender;
}
