package com.pocket.outbound.util;

import com.pocket.core.exception.jwt.SecurityCustomException;
import com.pocket.core.redis.util.RedisUtil;
import com.pocket.domain.dto.LoginResponse;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.pocket.core.exception.jwt.SecurityErrorCode.INVALID_TOKEN;
import static com.pocket.core.exception.jwt.SecurityErrorCode.TOKEN_EXPIRED;

@Component
@Slf4j
public class JwtUtil {

    private static final String AUTHORITIES_CLAIM_NAME = "auth";
    private static final String USERNAME = "username";

    private final SecretKey secretKey;
    private final Long accessExpMs;
    private final Long refreshExpMs;
    private final RedisUtil redisUtil;

    public JwtUtil(
            @Value("${security.jwt.secret}") String secret,
            @Value("${security.jwt.token.access-expiration-time}") Long access,
            @Value("${security.jwt.token.refresh-expiration-time}") Long refresh,
            RedisUtil redis) {

        secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        accessExpMs = access;
        refreshExpMs = refresh;
        redisUtil = redis;
    }

    public String createJwtAccessToken(String email, String subId) {
        Instant issuedAt = Instant.now();
        Instant expiration = issuedAt.plusMillis(accessExpMs);

        return Jwts.builder()
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                .setSubject(subId)
                .claim("email", email)
                .setIssuedAt(Date.from(issuedAt))
                .setExpiration(Date.from(expiration))
                .signWith(secretKey)
                .compact();
    }

    public String createJwtRefreshToken(String email, String subId) {
        Instant issuedAt = Instant.now();
        Instant expiration = issuedAt.plusMillis(refreshExpMs);

        String refreshToken = Jwts.builder()
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                .setSubject(subId)
                .claim("email", email)
                .setIssuedAt(Date.from(issuedAt))
                .setExpiration(Date.from(expiration))
                .signWith(secretKey)
                .compact();

        redisUtil.saveAsValue(
                email + "_refresh_token",
                refreshToken,
                refreshExpMs,
                TimeUnit.MILLISECONDS
        );

        return refreshToken;
    }

    public String resolveAccessToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            log.warn("[*] No token in req");
            return null;
        }

        log.info("[*] Token exists");
        return authorization.split(" ")[1];
    }

    public LoginResponse reissueToken(String refreshToken) {
        try {
            validateRefreshToken(refreshToken);
            log.info("[*] Valid RefreshToken");

            // 삭제 로직
            String email = getEmail(refreshToken);
            redisUtil.delete(email + "_refresh_token");

            String subId = getSubjectFromToken(refreshToken);

            return new LoginResponse(
                    createJwtAccessToken(email, subId),
                    createJwtRefreshToken(email, subId)
            );
        } catch (IllegalArgumentException iae) {
            throw new SecurityCustomException(INVALID_TOKEN, iae);
        } catch (ExpiredJwtException eje) {
            throw new SecurityCustomException(TOKEN_EXPIRED, eje);
        }
    }

    public void deleteToken(String refreshToken) {

        // 삭제 로직
        String email = getEmail(refreshToken);
        redisUtil.delete(email + "_refresh_token");
    }

    public void validateRefreshToken(String refreshToken) {
        // refreshToken 유효성 검증
        String email = getEmail(refreshToken);

        // redis에 refreshToken 있는지 검증
        if (!redisUtil.hasKey(email + "_refresh_token")) {
            log.warn("[*] case : Invalid refreshToken");
            throw new SecurityCustomException(INVALID_TOKEN);
        }
    }

    public String getSubjectFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public Long getId(String token) {
        return Long.parseLong(getClaims(token).getSubject());
    }

    public String getEmail(String token) {
        return getClaims(token).get("email", String.class);
    }

    public String getUsername(String token) {
        return getClaims(token).get(USERNAME, String.class);
    }

    public String getAuthority(String token) {
        return getClaims(token).get(AUTHORITIES_CLAIM_NAME, String.class);
    }

    public Boolean isExpired(String token) {
        // 여기서 토큰 형식 이상한 것도 걸러짐
        return getClaims(token).getExpiration().before(Date.from(Instant.now()));
    }

    public Long getExpTime(String token) {
        return getClaims(token).getExpiration().getTime();
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            throw new SecurityCustomException(INVALID_TOKEN, e);
        }
    }
}
