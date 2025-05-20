package com.martynov.user_service.user.infrastructure.persistance;

import com.martynov.user_service.user.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(final String username);
    Optional<User> findByUsername(final String username);
}
