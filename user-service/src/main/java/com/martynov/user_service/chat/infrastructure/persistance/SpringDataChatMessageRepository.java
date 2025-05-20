package com.martynov.user_service.chat.infrastructure.persistance;

import com.martynov.user_service.chat.domain.ChatMessage;
import com.martynov.user_service.chat.domain.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SpringDataChatMessageRepository implements ChatMessageRepository {
    private final JpaChatMessageRepository chatMessageRepository;


    @Override
    public List<ChatMessage> retrieveChat(Long user1, Long user2) {
        return chatMessageRepository.retrieveChat(user1, user2);
    }

    @Override
    public void save(ChatMessage chatMessage) {
        chatMessageRepository.save(chatMessage);
    }
}
