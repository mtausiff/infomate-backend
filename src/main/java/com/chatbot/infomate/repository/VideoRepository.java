package com.chatbot.infomate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.chatbot.infomate.model.Video;
import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    Optional<Video> findById(Long id);

    List<Video> findAll();

    List<Video> findByQuestionId(Long questionId);

    Video save(Video video);

    void deleteById(Long id);

    boolean existsById(Long id);
}
