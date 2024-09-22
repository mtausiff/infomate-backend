package com.chatbot.infomate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.chatbot.infomate.model.Category;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findById(Long id);

    List<Category> findAll();

    Optional<Category> findByCategoryName(String categoryName);

    List<Category> findAllByCategoryName(String categoryName);

    Category save(Category category);

    void deleteById(Long id);

    boolean existsById(Long id);
}
