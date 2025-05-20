package com.martynov.user_service.like.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_like")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "track_id")
    private Long trackId;

    @CreationTimestamp
    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    public static Like create(final Long userId, final Long trackId) {
        return new Like(null, userId, trackId, null);
    }
}
