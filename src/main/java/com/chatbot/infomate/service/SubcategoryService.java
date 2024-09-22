package com.chatbot.infomate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chatbot.infomate.model.Subcategory;
import com.chatbot.infomate.repository.SubcategoryRepository;
import java.util.List;
import java.util.Optional;

@Service
public class SubcategoryService {
    @Autowired
    private SubcategoryRepository subcategoryRepository;

    public List<Subcategory> getAllSubcategoriesByCategoryId(Long categoryId) {
        return subcategoryRepository.findAllByCategoryId(categoryId);
    }

    public Optional<Subcategory> getSubcategoryByCategoryId(String categoryName) {
        return subcategoryRepository.findBySubcategoryName(categoryName);
    }
}
