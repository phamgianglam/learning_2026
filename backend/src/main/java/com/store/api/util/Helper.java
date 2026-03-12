package com.store.api.util;

import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.time.ZonedDateTime;

@Component
public class Helper {

    // Injected as instance field then assigned to static for usage in static methods
    @Value("${app.jwt.token-validity-in-seconds:3600}")
    private long tokenValidityInSecondsInjected;

    private static long tokenValidityInSeconds;

    @Value("${app.jwt.secret:MTIzNDU2Nzg5MDEyMzQ1Njc4OTAxMjM0NTY3ODkwMTI=}")
    private String secretKey;

    private SecretKey key;

    public static boolean isExpiredToken(ZonedDateTime createdTimeStamp) {
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime expiryTimeStamp = createdTimeStamp.plusSeconds(tokenValidityInSeconds);
        return now.isAfter(expiryTimeStamp);
    }

    @PostConstruct
    public void init() {
        // assign injected value to static used by static method
        tokenValidityInSeconds = tokenValidityInSecondsInjected;
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(Long userId) {
        // use standard builder API; the subject will be read back by the parser below
        return Jwts.builder()
                .subject(userId.toString())
                .issuedAt(new Date(System.currentTimeMillis()))
                .signWith(this.key)
                .compact();
    }

    public String getUserIdFromToken(String token) {
        // parse the JWT using the same signing key and return the subject (the user id)
        Claims claims = Jwts.parser()
                .verifyWith(this.key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }
}
