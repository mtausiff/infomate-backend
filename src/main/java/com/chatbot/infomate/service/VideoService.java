package com.chatbot.infomate.service;

import com.chatbot.infomate.dto.VideoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chatbot.infomate.repository.VideoRepository;
import com.chatbot.infomate.model.Video;
import java.util.List;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;

    public List<Video> getVideosByQuestionId(Long questionId) {
        return videoRepository.findByQuestionId(questionId);
    }

    public void addVideoByQuestionId(Long questionId, VideoDTO videoDTO) {
        //logic to save video and assign to question
    }

    public void deleteVideoById(Long videoId) {
        //logic to delete video
    }
}
