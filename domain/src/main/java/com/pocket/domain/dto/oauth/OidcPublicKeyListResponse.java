package com.pocket.domain.dto.oauth;

import java.util.List;

public record OidcPublicKeyListResponse(
        List<OidcPublicKeyResponse> keys
) {
}
