package com.finance.api.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

@Service
public class JwtService {
    private final JwtProperties jwtProperties;
    private SecretKey secretKey;

    public JwtService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        this.secretKey = io.jsonwebtoken.security.Keys
                .hmacShaKeyFor(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(UserDetails userDetails) {
        JwtBuilder builder = Jwts.builder();
        builder.subject(userDetails.getUsername());
        builder.issuedAt(new Date());
        builder.expiration(new Date(System.currentTimeMillis() + jwtProperties.getExpirationMs()));
        builder.signWith(this.secretKey);
        return builder.compact();
    }

    public boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser().verifyWith(this.secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
        return expiration.before(new Date());
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        String username = Jwts.parser().verifyWith(this.secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
        return username;
    }
}