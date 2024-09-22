package com.chatbot.infomate.model;

import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "questions")
@Slf4j
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    private String questionText;
    private String answerText;
    private int popularityCount;

    @ManyToOne
    @JoinColumn(name = "subcategory_id", nullable = false)
    private Subcategory subcategory;

    public Question() {
    }

    public Question(String questionText, String answerText, Subcategory subcategory, int popularityCount){
        this.questionText = questionText;
        this.answerText = answerText;
        this.popularityCount = popularityCount;
        this.subcategory = subcategory;
    }
}
