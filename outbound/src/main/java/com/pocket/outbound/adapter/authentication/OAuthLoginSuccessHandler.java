package com.pocket.outbound.adapter.authentication;

import com.pocket.outbound.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuthLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Value("${security.jwt.token.redirect-uri}")
    private String redirectUrl;

    private final JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        if (authentication.getPrincipal() instanceof OidcUser oidcUser) {
            final String jwtAccessToken = jwtUtil.createJwtAccessToken(oidcUser.getEmail(), oidcUser.getSubject());
            final String jwtRefreshToken = jwtUtil.createJwtRefreshToken(oidcUser.getEmail(), oidcUser.getSubject());

            String encodedRedirectUrl = UriComponentsBuilder.fromUriString(redirectUrl)
                    .queryParam("accessToken", jwtAccessToken)
                    .queryParam("refreshToken", jwtRefreshToken)
                    .build(true)
                    .toUriString();

            response.sendRedirect(encodedRedirectUrl);
        } else {
            // 인증 타입이 OidcUser가 아닐 경우 처리
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Authentication principal is not an instance of OidcUser");
        }
    }
}

