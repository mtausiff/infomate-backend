package com.chatbot.infomate.service;

import com.chatbot.infomate.dto.VideoDTO;
import com.chatbot.infomate.model.Question;
import com.chatbot.infomate.repository.QuestionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chatbot.infomate.repository.VideoRepository;
import com.chatbot.infomate.model.Video;
import java.util.List;
import java.util.Optional;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private QuestionRepository questionRepository;

    public List<Video> getVideosByQuestionId(Long questionId) {
        return videoRepository.findByQuestionId(questionId);
    }

    public Video addVideoByQuestionId(Long questionId, VideoDTO videoDTO) {
        Question question = questionRepository.findById(questionId).orElseThrow();
        ObjectMapper mapper = new ObjectMapper();
        Video video = mapper.convertValue(videoDTO, Video.class);
        video.setQuestion(question);
        return videoRepository.save(video);
    }

    public void deleteVideoById(Long videoId) {
        if(videoRepository.findById(videoId).isEmpty()){
            throw new IllegalArgumentException("Video not found for given Id");
        }
        videoRepository.deleteById(videoId);
    }

    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    public Optional<Video> getVideoById(Long id) {
        return videoRepository.findById(id);
    }

    public Video updateVideo(Long id, Video video) {
        if(videoRepository.findById(id).isEmpty()){
            throw new IllegalArgumentException("Video not found for given Id");
        }
        return videoRepository.save(video);
    }
}
