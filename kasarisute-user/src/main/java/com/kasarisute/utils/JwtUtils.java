package com.kasarisute.utils;

import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import ch.qos.logback.core.util.StringUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "markerhub.jwt")
public class JwtUtils {
    private long expire;
    private String secret;
    private String header;

    public String generateToken(Map<String, ?> Claims) {
        Instant instant = Instant.now();
        Instant expireInstant = instant.plusSeconds(TimeUnit.DAYS.toSeconds(expire));
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

        return Jwts.builder()
                .header()
                .add("type", "JWT")
                .and()
                .claims(Claims)
                .issuedAt(Date.from(instant))
                .expiration(Date.from(expireInstant))
                .signWith(key)
                .compact();

    }

    public String generateToken(String userName) {

        Instant instant = Instant.now();

        Instant expireInstant = instant.plusSeconds(TimeUnit.DAYS.toSeconds(expire));

        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

        return Jwts.builder()
                .header()
                .add("type", "JWT")
                .and()
                .subject(userName)
                .issuedAt(Date.from(instant))
                .expiration(Date.from(expireInstant))
                .signWith(key)
                .compact();
    }

    public Claims getClaimsByToken(String jwt) {
        try {

            SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(jwt)
                    .getPayload();

        } catch (Exception e) {
            return null;
        }
    }

    public Claims getClaimsByAuthorizationToken(String token) {
        String beareString = "Bearer ";
        if (StringUtil.isNullOrEmpty(token))
            return null;
        if (token.startsWith(beareString)) {
            return this.getClaimsByToken(token.substring(beareString.length()));
        }
        return this.getClaimsByToken(token);
    }

    public Boolean tokenIsExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }
}
