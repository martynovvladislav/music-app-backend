package com.martynov.user_service.common.infrastructure.api.rest.auth;

import com.martynov.user_service.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Not implemented");
    }

    public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
        return userRepository.findById(id)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getId().toString(), user.getPassword(), List.of()))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
