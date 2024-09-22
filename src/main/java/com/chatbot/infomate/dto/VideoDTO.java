package com.chatbot.infomate.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Data
public class VideoDTO {
    private Long videoId;
    private Long name;
    private String description;
    private String url;

}