package com.pocket.outbound.decoder;

import com.pocket.core.exception.jwt.SecurityCustomException;
import com.pocket.core.exception.jwt.SecurityErrorCode;
import com.pocket.domain.dto.OidcDecodePayload;
import io.jsonwebtoken.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OidcJwtDecoder {
    private static final String KID = "kid";

    public static String getKidFromUnsignedTokenHeader(String token, String iss, String aud) {
        return (String) getUnsignedTokenClaims(token, iss, aud).getHeader().get(KID);
    }

    private static Jwt<Header, Claims> getUnsignedTokenClaims(String token, String iss, String aud) {
        try {
            return Jwts.parserBuilder()
                    .requireAudience(aud)
                    .requireIssuer(iss)
                    .build()
                    .parseClaimsJwt(getUnsignedToken(token));
        } catch (ExpiredJwtException e) {
            throw new SecurityCustomException(SecurityErrorCode.TOKEN_EXPIRED);
        } catch (Exception e) {
            log.error("ID 토큰 파싱중 오류 발생: {}", e.getMessage());
            throw new SecurityCustomException(SecurityErrorCode.INVALID_TOKEN);
        }
    }

    private static String getUnsignedToken(String token) {
        String[] splitToken = token.split("\\.");
        if (splitToken.length != 3) {
            throw new SecurityCustomException(SecurityErrorCode.INVALID_TOKEN);
        }
        return splitToken[0] + "." + splitToken[1] + ".";
    }

    public static Jws<Claims> getOidcTokenJws(String token, String modulus, String exponent) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getRsaPublicKey(modulus, exponent))
                    .build()
                    .parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw new SecurityCustomException(SecurityErrorCode.TOKEN_EXPIRED);
        } catch (Exception e) {
            log.error("ID 토큰 파싱중 오류 발생: {}", e.getMessage());
            throw new SecurityCustomException(SecurityErrorCode.INVALID_TOKEN);
        }
    }

    public static OidcDecodePayload getOidcTokenBody(String token, String modulus, String exponent) {
        Claims body = getOidcTokenJws(token, modulus, exponent).getBody();

        System.out.println(body);

        return new OidcDecodePayload(
                body.getIssuer(),
                body.getAudience().toString(),
                body.getSubject(),
                body.get("nickname", String.class),
                body.get("picture", String.class),
                body.get("email", String.class)
        );
    }

    private static Key getRsaPublicKey(String modulus, String exponent)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodeN = Base64.getUrlDecoder().decode(modulus);
        byte[] decodeE = Base64.getUrlDecoder().decode(exponent);
        BigInteger n = new BigInteger(1, decodeN);
        BigInteger e = new BigInteger(1, decodeE);

        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(n, e);
        return keyFactory.generatePublic(keySpec);
    }
}
