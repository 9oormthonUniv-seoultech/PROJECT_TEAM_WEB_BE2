package com.pocket.outbound.adapter;

import com.pocket.domain.dto.OidcDecodePayload;
import com.pocket.domain.entity.User;
import com.pocket.outbound.entity.JpaUser;
import com.pocket.outbound.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoLoginAdapter extends OidcUserService {

    private final UserRepository userRepository;
    private final KakaoIdTokenDecodeAdapter kakaoIdTokenDecodeAdapter;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        try {
            // Access Token 가져오기
            String tokenValue = userRequest.getAccessToken().getTokenValue();
            log.debug("Access Token: {}", tokenValue);

            // Access Token에서 유저 정보 디코딩 (Kakao 특화)
            OidcDecodePayload oidcDecodePayload = kakaoIdTokenDecodeAdapter.getPayloadFromAccessToken(tokenValue);
            log.debug("Decoded Payload: {}", oidcDecodePayload);

            // User 존재 여부 확인 및 조회
            JpaUser user = userRepository.findByUserSubId(oidcDecodePayload.email())
                    .orElseGet(() -> {
                        User newUser = User.createSocialUser(oidcDecodePayload);
                        JpaUser savedUser = JpaUser.createUser(newUser);
                        return userRepository.save(savedUser);
                    });

            OidcUser oidcUser = new DefaultOidcUser(
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                    userRequest.getIdToken()
            );

            return oidcUser;

        } catch (Exception e) {
            log.error("Error loading user from OAuth2 user request: {}", e.getMessage(), e);
            throw new OAuth2AuthenticationException("Error loading user from OAuth2 user request: " + e.getMessage());
        }
    }

}
