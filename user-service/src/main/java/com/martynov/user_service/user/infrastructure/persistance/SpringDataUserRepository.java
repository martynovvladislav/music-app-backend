package com.martynov.user_service.user.infrastructure.persistance;

import com.martynov.user_service.user.domain.model.User;
import com.martynov.user_service.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SpringDataUserRepository implements UserRepository {
    private final JpaUserRepository jpaUserRepository;

    @Override
    public boolean existsByUsername(final String username) {
        return jpaUserRepository.existsByUsername(username);
    }

    @Override
    public void save(User user) {
        jpaUserRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaUserRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return jpaUserRepository.findByUsername(username);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaUserRepository.existsById(id);
    }

}
