package com.chatbot.infomate.dto;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CategoryDTO {
    private String categoryName;
    private Long categoryId;
    private List<String> subcategories;
    private List<String> questions;

}