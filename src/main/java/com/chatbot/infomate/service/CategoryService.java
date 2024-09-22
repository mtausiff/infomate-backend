package com.chatbot.infomate.service;

import java.util.List;

import com.chatbot.infomate.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chatbot.infomate.model.Category;
import com.chatbot.infomate.repository.CategoryRepository;


@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void addCategory(CategoryDTO categoryDTO) {

    }

    public void deleteCategory(Long categoryId) {

    }
}
