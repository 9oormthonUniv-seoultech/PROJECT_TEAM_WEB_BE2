package com.pocket.core.exception.jwt.dto;

public record JwtPair(
	String accessToken,
	String refreshToken
) {
}
