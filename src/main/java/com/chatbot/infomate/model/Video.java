package com.chatbot.infomate.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Entity
@Table(name = "videos")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String videoLink;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    public Video() {
    }

    public Video(String videoLink, Question question) {
        this.videoLink = videoLink;
        this.question = question;
    }
}
