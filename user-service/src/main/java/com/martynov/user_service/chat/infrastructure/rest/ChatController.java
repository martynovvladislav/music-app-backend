package com.martynov.user_service.chat.infrastructure.rest;

import com.martynov.user_service.chat.application.dto.ChatMessageDto;
import com.martynov.user_service.chat.domain.ChatMessage;
import com.martynov.user_service.chat.domain.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageRepository chatMessageRepository;

    @MessageMapping("/send")
    public void sendMessage(final ChatMessageDto msg) {
        System.out.println(msg);
        final ChatMessage entity = msg.convert();
        chatMessageRepository.save(entity);

        messagingTemplate.convertAndSend(
                "/topic/chat/" + msg.toUserId,
                msg
        );
    }

    @GetMapping("/{friendId}")
    public List<ChatMessage> getMessages(@PathVariable Long friendId,
                                         @AuthenticationPrincipal final UserDetails userDetails) {
        final Long currentUserId = Long.parseLong(userDetails.getUsername());
        return chatMessageRepository.retrieveChat(currentUserId, friendId);
    }
}

