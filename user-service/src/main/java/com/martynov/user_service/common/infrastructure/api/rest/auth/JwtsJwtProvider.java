package com.martynov.user_service.common.infrastructure.api.rest.auth;

import com.martynov.user_service.user.application.service.UserTokenProvider;
import com.martynov.user_service.user.domain.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtsJwtProvider implements UserTokenProvider {

    @Value("${jwt.secret-key}")
    private String jwtSecret;
    @Value("${jwt.expiration-time}")
    private long jwtExpirationMs;

    public String generateToken(final User user) {
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public Long getUserIdFromToken(final String token) {
        final String userIdAsString = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        return Long.parseLong(userIdAsString);
    }

    public boolean validateToken(final String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
