package com.chatbot.infomate.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.chatbot.infomate.model.Subcategory;
import com.chatbot.infomate.service.SubcategoryService;

import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "SubCategory", description = "SubCategory Controller")
@RestController
@RequestMapping("/api/subcategories")
public class SubcategoryController {
    @Autowired
    private SubcategoryService subcategoryService;

    @Operation(summary = "Get subcategories by category ID", description = "Returns a list of subcategories for a specific category.")
    @GetMapping("/{categoryId}")
    public ResponseEntity<List<Subcategory>> getSubcategoriesByCategoryId(
            @Parameter(description = "ID of the category to retrieve subcategories for") @PathVariable Long categoryId) {
        List<Subcategory> subcategories = subcategoryService.getAllSubcategoriesByCategoryId(categoryId);
        return new ResponseEntity<>(subcategories, HttpStatus.OK);
    }

    @Operation(summary = "Get all subcategories", description = "Returns a list of all subcategories.")
    @GetMapping
    public ResponseEntity<List<Subcategory>> getAllSubcategories() {
        List<Subcategory> subcategories = subcategoryService.getAllSubcategories();
        return new ResponseEntity<>(subcategories, HttpStatus.OK);
    }

    @Operation(summary = "Get subcategory by ID", description = "Returns a subcategory by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Subcategory> getSubcategoryById(@PathVariable Long id) {
        return subcategoryService.getSubcategoryById(id)
                .map(subcategory -> new ResponseEntity<>(subcategory, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Create subcategory", description = "Creates a new subcategory.")
    @PostMapping
    public ResponseEntity<Subcategory> createSubcategory(@RequestBody Subcategory subcategory) {
        Subcategory createdSubcategory = subcategoryService.saveSubcategory(subcategory);
        return new ResponseEntity<>(createdSubcategory, HttpStatus.CREATED);
    }

    @Operation(summary = "Update subcategory", description = "Updates an existing subcategory.")
    @PutMapping("/{id}")
    public ResponseEntity<Subcategory> updateSubcategory(@PathVariable Long id, @RequestBody Subcategory subcategory) {
        Subcategory updatedSubcategory = subcategoryService.saveSubcategory(subcategory);
        return new ResponseEntity<>(updatedSubcategory, HttpStatus.OK);
    }

    @Operation(summary = "Delete subcategory", description = "Deletes a subcategory by its ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubcategory(@PathVariable Long id) {
        subcategoryService.deleteSubcategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
