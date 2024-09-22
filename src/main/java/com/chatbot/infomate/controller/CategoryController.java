package com.chatbot.infomate.controller;


import com.chatbot.infomate.dto.CategoryDTO;
import com.chatbot.infomate.dto.VideoDTO;
import com.chatbot.infomate.model.Category;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.chatbot.infomate.model.Category;
import com.chatbot.infomate.service.CategoryService;

import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Category", description = "Category Controller")
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "get User details by accountId & role", description = "returns User details by accountId & role.")
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @Operation(summary = "get Category details by accountId & role", description = "returns Category details by accountId & role.")
    @PostMapping("/")
    public ResponseEntity addCategory(@Parameter(name = "type", description = "type of Category", example = "registered,verifier,admin") @PathVariable CategoryDTO categoryDTO) {
        categoryService.addCategory(categoryDTO);
        return ResponseEntity.ok(null);
    }

    @Operation(summary = "get Category details by accountId & role", description = "returns Category details by accountId & role.")
    @DeleteMapping("/{categoryId}")
    public ResponseEntity deleteVideoById(@Parameter(name = "type", description = "type of Category", example = "registered,verifier,admin") @PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok(null);
    }
}
