package com.kCalControl.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.Serial;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenManager implements Serializable {

    @Serial
    private static final long serialVersionUID = 1391257847883286658L;
    public static final SignatureAlgorithm ALGORITHM = SignatureAlgorithm.HS256;
    public static final long TOKEN_VALIDITY = 60000 * 30; //30 min
    public static final long REFRESH_TOKEN_VALIDITY = 60000 * 60 * 24 * 7; //7 days

    byte[] signingKey;
    SecretKey secretKey;

    @Autowired
    public void setJWTBase64Secret(@Value("${jwt.base64secret}") String jwtSecret) {
        this.signingKey = Decoders.BASE64.decode(jwtSecret);
        this.secretKey = Keys.hmacShaKeyFor(this.signingKey);
    }


    // Generate a JWT token with the provided subject
    public String generateJwtToken(String subject, String roleName, Date expiryDate) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("ROLE", roleName);
        claims.put("EXPIRATION_DATE", expiryDate);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY))
                .signWith(this.secretKey, ALGORITHM)
                .compact();
    }

    public String generateRefreshToken(String subject, String roleName, Date expiryDate) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("ROLE", roleName);
        claims.put("EXPIRATION_DATE", expiryDate);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALIDITY))
                .signWith(this.secretKey, ALGORITHM)
                .compact();
    }

    // Parse and retrieve claims from a JWT token
    public Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(this.signingKey)
                .parseClaimsJws(token)
                .getBody();
    }

    // Validate if the token's claims have expired
    public boolean validateClaims(Claims claims) {
        var isTokenExpired = claims.getExpiration().before(new Date());
        return !isTokenExpired;
    }

    // Retrieve the subject from the token's claims
    public String getSubject(@NotNull Claims claims) {
        return claims.getSubject();
    }
}
