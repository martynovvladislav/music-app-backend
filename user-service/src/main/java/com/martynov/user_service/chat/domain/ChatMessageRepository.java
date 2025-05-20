package com.martynov.user_service.chat.domain;

import java.util.List;

public interface ChatMessageRepository {
    List<ChatMessage> retrieveChat(Long user1, Long user2);

    void save(ChatMessage chatMessage);
}
