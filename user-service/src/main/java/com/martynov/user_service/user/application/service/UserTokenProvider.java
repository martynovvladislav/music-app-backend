package com.martynov.user_service.user.application.service;

import com.martynov.user_service.user.domain.model.User;

public interface UserTokenProvider {
    String generateToken(final User user);

    Long getUserIdFromToken(final String token);

    boolean validateToken(final String token);
}
