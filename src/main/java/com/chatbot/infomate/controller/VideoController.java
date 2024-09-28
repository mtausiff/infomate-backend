package com.chatbot.infomate.controller;

import com.chatbot.infomate.dto.VideoDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.chatbot.infomate.model.Video;
import com.chatbot.infomate.service.VideoService;

import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Video", description = "Video Controller")
@RestController
@RequestMapping("/api/videos")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @Operation(summary = "Get videos by question ID", description = "Returns a list of videos associated with a specific question.")
    @GetMapping("/question/{questionId}")
    public ResponseEntity<List<Video>> getVideosByQuestionId(
            @Parameter(description = "ID of the question to retrieve videos for") @PathVariable Long questionId) {
        List<Video> videos = videoService.getVideosByQuestionId(questionId);
        return new ResponseEntity<>(videos, HttpStatus.OK);
    }

    @Operation(summary = "Add video to a question", description = "Adds a new video to a specific question.")
    @PostMapping("/question/{questionId}")
    public ResponseEntity addVideoByQuestionId(
            @Parameter(description = "ID of the question to add the video to") @PathVariable Long questionId,
            @Parameter(description = "Video details to be added") @RequestBody VideoDTO videoDTO) {
        videoService.addVideoByQuestionId(questionId, videoDTO);
        return ResponseEntity.ok(null);
    }

    @Operation(summary = "Delete video", description = "Deletes a video by its ID.")
    @DeleteMapping("/{videoId}")
    public ResponseEntity deleteVideoById(
            @Parameter(description = "ID of the video to be deleted") @PathVariable Long videoId) {
        videoService.deleteVideoById(videoId);
        return ResponseEntity.ok(null);
    }

    @Operation(summary = "Get all videos", description = "Returns a list of all videos.")
    @GetMapping
    public ResponseEntity<List<Video>> getAllVideos() {
        List<Video> videos = videoService.getAllVideos();
        return new ResponseEntity<>(videos, HttpStatus.OK);
    }

    @Operation(summary = "Get video by ID", description = "Returns a video by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Video> getVideoById(@PathVariable Long id) {
        return videoService.getVideoById(id)
                .map(video -> new ResponseEntity<>(video, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Update video", description = "Updates an existing video.")
    @PutMapping("/{id}")
    public ResponseEntity<Video> updateVideo(@PathVariable Long id, @RequestBody Video video) {
        Video updatedVideo = videoService.updateVideo(id, video);
        return new ResponseEntity<>(updatedVideo, HttpStatus.OK);
    }
}
