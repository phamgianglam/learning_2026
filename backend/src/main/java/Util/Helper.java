package Util;

import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.time.ZonedDateTime;

@Component
public class Helper {

    @Value("${app.jwt.token-validity-in-seconds}")
    private static long tokenValidityInSeconds;

    @Value("${app.jwt.secret}")
    private String secretKey;

    private Key key;

    public static boolean isExpiredToken(ZonedDateTime createdTimeStamp) {
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime expiryTimeStamp = createdTimeStamp.plusSeconds(tokenValidityInSeconds);
        return now.isAfter(expiryTimeStamp);
    }

    @PostConstruct
    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken (Long userId) {
        return Jwts.builder().subject(userId.toString()).issuedAt(new Date(System.currentTimeMillis())).signWith(this.key).compact();
    }

    public String getUserIdFromToken(String token) {
        return Jwts.parser().verifyWith((SecretKey) this.key)
                .build().parseSignedClaims(token).getPayload().getSubject();
    }
}
