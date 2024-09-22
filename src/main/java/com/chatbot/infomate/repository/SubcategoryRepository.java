package com.chatbot.infomate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.chatbot.infomate.model.Subcategory;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
    Optional<Subcategory> findById(Long id);

    List<Subcategory> findAll();

    Optional<Subcategory> findBySubcategoryName(String subcategoryName);

    List<Subcategory> findAllBySubcategoryName(String subcategoryName);

    List<Subcategory> findAllByCategoryId(Long categoryId);

    Subcategory save(Subcategory subcategory);

    void deleteById(Long id);

    boolean existsById(Long id);
}