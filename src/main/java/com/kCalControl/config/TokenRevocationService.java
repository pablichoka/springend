package com.kCalControl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class TokenRevocationService {

    @Autowired
    StringRedisTemplate redisTemplate;

    private static final String REVOKED_TOKENS_KEY = "revoked_tokens";

    public void revokeToken(String token) {
        redisTemplate.opsForSet().add(REVOKED_TOKENS_KEY, token);
    }

    public boolean isTokenRevoked(String token) {
        return Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(REVOKED_TOKENS_KEY, token));
    }
}
