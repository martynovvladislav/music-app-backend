package com.martynov.user_service.chat.infrastructure.persistance;

import com.martynov.user_service.chat.domain.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    @Query(value = """
        SELECT * FROM chat_message cm
        WHERE (cm.from_user_id = :user1 AND cm.to_user_id = :user2)
           OR (cm.from_user_id = :user2 AND cm.to_user_id = :user1)
        ORDER BY cm.timestamp ASC
    """, nativeQuery = true)
    List<ChatMessage> retrieveChat(Long user1, Long user2);
}
