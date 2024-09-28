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

    @Operation(summary = "Add new category", description = "Adds a new category and returns the created category details.")
    @PostMapping("/")
    public ResponseEntity addCategory(
            @Parameter(description = "Category details to be added") @RequestBody CategoryDTO categoryDTO) {
        categoryService.addCategory(categoryDTO);
        return ResponseEntity.ok(null);
    }

    @Operation(summary = "Delete category", description = "Deletes a category by its ID.")
    @DeleteMapping("/{categoryId}")
    public ResponseEntity deleteCategory(
            @Parameter(description = "ID of the category to be deleted") @PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok(null);
    }

    @Operation(summary = "Get category by ID", description = "Returns a category by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id)
                .map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Update category", description = "Updates an existing category.")
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        Category updatedCategory = categoryService.updateCategory(id, category);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @Operation(summary = "Get category by name", description = "Returns a category by its name.")
    @GetMapping("/name/{name}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable String name) {
        return categoryService.getCategoryByName(name)
                .map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
