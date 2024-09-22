package com.chatbot.infomate.service;

import java.util.List;
import java.util.Optional;

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

    public Category addCategory(CategoryDTO categoryDTO) {
        Category category = new Category(); // Convert DTO to entity
        // Set category properties from DTO
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long categoryId) {
        if(categoryRepository.findById(categoryId).isEmpty()){
            throw new IllegalArgumentException("Category not found for given Id");
        }
        categoryRepository.deleteById(categoryId);
    }

    // Added CRUD methods
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category updateCategory(Long categoryId, Category category) {
        if(categoryRepository.findById(categoryId).isEmpty()){
            throw new IllegalArgumentException("Category not found for given Id");
        }
        return categoryRepository.save(category);
    }

    public Optional<Category> getCategoryByName(String name) {
        return categoryRepository.findByCategoryName(name);
    }
}
