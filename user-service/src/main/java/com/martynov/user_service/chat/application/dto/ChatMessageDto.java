package com.martynov.user_service.chat.application.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.martynov.user_service.chat.domain.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@ToString
public class ChatMessageDto {
    public Long fromUserId;
    public Long toUserId;
    public Long trackId;
    public Long albumId;
    public String trackTitle;
    public String artistName;
    public String coverUrl;
    public LocalDateTime timestamp;

    public ChatMessage convert() {
        return new ChatMessage(null, fromUserId, toUserId, trackId, albumId, trackTitle, artistName, coverUrl, LocalDateTime.now());
    }
}
