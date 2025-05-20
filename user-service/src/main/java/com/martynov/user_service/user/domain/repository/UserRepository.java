package com.martynov.user_service.user.domain.repository;

import com.martynov.user_service.user.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    boolean existsByUsername(final String username);

    void save(final User user);

    Optional<User> findById(final Long id);

    Optional<User> findByUsername(final String username);

    boolean existsById(final Long id);
}
