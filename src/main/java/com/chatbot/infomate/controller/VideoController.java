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

    @Operation(summary = "get Video details by accountId & role", description = "returns Video details by accountId & role.")
    @GetMapping("/question/{questionId}")
    public ResponseEntity<List<Video>> getVideosByQuestionId(@Parameter(name = "type", description = "type of Video", example = "registered,verifier,admin") @PathVariable Long questionId) {
        List<Video> videos = videoService.getVideosByQuestionId(questionId);
        return new ResponseEntity<>(videos, HttpStatus.OK);
    }

    @Operation(summary = "get Video details by accountId & role", description = "returns Video details by accountId & role.")
    @PostMapping("/question/{questionId}")
    public ResponseEntity addVideoByQuestionId(@Parameter(name = "type", description = "type of Video", example = "registered,verifier,admin") @PathVariable Long questionId,
                                                @Parameter(name = "type", description = "type of Video", example = "registered,verifier,admin") @PathVariable VideoDTO videoDTO) {
        videoService.addVideoByQuestionId(questionId, videoDTO);
        return ResponseEntity.ok(null);
    }

    @Operation(summary = "get Video details by accountId & role", description = "returns Video details by accountId & role.")
    @DeleteMapping("/{videoId}")
    public ResponseEntity deleteVideoById(@Parameter(name = "type", description = "type of Video", example = "registered,verifier,admin") @PathVariable Long videoId) {
        videoService.deleteVideoById(videoId);
        return ResponseEntity.ok(null);
    }
}
