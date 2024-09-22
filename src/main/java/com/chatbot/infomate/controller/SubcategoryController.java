package com.chatbot.infomate.controller;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @Operation(summary = "get User details by accountId & role", description = "returns User details by accountId & role.")
    @GetMapping("/{categoryId}")
    public ResponseEntity<List<Subcategory>> getSubcategoriesByCategoryId(@Parameter(name = "type", description = "type of User", example = "registered,verifier,admin") @PathVariable Long categoryId) {
        List<Subcategory> subcategories = subcategoryService.getAllSubcategoriesByCategoryId(categoryId);
        return new ResponseEntity<>(subcategories, HttpStatus.OK);
    }
}
