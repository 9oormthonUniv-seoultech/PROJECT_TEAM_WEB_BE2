package com.pocket.core.exception.jwt.dto;

public record JwtDto(
	String accessToken,
	String refreshToken
) {
}
