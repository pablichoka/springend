package com.kCalControl.config;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TokenRevocationService {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    TokenManager tokenManager;

    private static final String REVOKED_TOKENS_KEY = "revoked_tokens";

    public void revokeToken(String token) {
        redisTemplate.opsForSet().add(REVOKED_TOKENS_KEY, token);
    }

    public boolean isTokenRevoked(String token) {
        return Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(REVOKED_TOKENS_KEY, token));
    }

    @Scheduled(fixedRate = 60000L * 60000L) // Check every 3600 seconds
    public void cleanupExpiredTokens() {
        Set<String> tokens = redisTemplate.opsForSet().members(REVOKED_TOKENS_KEY);
        if (tokens == null) {
            return;
        }
        for (String token : tokens) {
            try {
                tokenManager.validateClaims(tokenManager.parseToken(token));
            } catch (ExpiredJwtException e) {
                redisTemplate.opsForSet().remove(REVOKED_TOKENS_KEY, token);
            }
        }
    }
}
