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

    public Optional<Subcategory> getSubcategoryByName(String subcategoryName) {
        return subcategoryRepository.findBySubcategoryName(subcategoryName);
    }

    public List<Subcategory> getAllSubcategories() {
        return subcategoryRepository.findAll();
    }

    public Optional<Subcategory> getSubcategoryById(Long id) {
        return subcategoryRepository.findById(id);
    }

    public Subcategory saveSubcategory(Subcategory subcategory) {
        return subcategoryRepository.save(subcategory);
    }

    public void deleteSubcategory(Long id) {
        if(subcategoryRepository.findById(id).isEmpty()){
            throw new IllegalArgumentException("Subcategory not found for given Id");
        }
        subcategoryRepository.deleteById(id);
    }
}
