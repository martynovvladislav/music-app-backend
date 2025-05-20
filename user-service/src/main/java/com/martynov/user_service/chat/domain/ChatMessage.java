package com.martynov.user_service.chat.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "chat_message")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_user_id")
    private Long fromUserId;

    @Column(name = "to_user_id")
    private Long toUserId;

    @Column(name = "track_id")
    private Long trackId;

    @Column(name = "album_id")
    private Long albumId;

    @Column(name = "track_title")
    private String trackTitle;

    @Column(name = "artist_name")
    private String artistName;

    @Column(name = "cover_url")
    private String coverUrl;

    private LocalDateTime timestamp;
}
