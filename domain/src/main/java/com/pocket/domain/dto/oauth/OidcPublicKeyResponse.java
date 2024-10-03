package com.pocket.domain.dto.oauth;

public record OidcPublicKeyResponse(
        String kid,
        String kty,
        String alg,
        String use,
        String n,
        String e
) {
}
