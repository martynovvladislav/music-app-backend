package com.martynov.user_service.like.infrastructure.persistance;

import com.martynov.user_service.like.domain.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaLikeRepository extends JpaRepository<Like, Long> {
    boolean existsByUserIdAndTrackId(Long userId, Long trackId);

    void deleteByUserIdAndTrackId(Long userId, Long trackId);

    List<Like> findAllByUserId(Long userId);
}
