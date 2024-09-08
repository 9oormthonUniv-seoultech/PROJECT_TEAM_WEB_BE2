package com.pocket.domain.dto;

public record OidcPublicKeyResponse(
        String kid,
        String kty,
        String alg,
        String use,
        String n,
        String e
) {
}
