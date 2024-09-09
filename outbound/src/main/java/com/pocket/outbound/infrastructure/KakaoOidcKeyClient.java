package com.pocket.outbound.infrastructure;

import com.pocket.domain.dto.OidcPublicKeyListResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/.well-known")
public class KakaoOidcKeyClient {

    private final RestTemplate restTemplate;

    public KakaoOidcKeyClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable(cacheNames = "KakaoOidcKey", cacheManager = "oidcCacheManager")
    @GetMapping("/jwks.json")
    public OidcPublicKeyListResponse getKakaoOidcOpenKeys() {
        String url = "https://kauth.kakao.com/.well-known/jwks.json"; // Replace with your actual base URL
        return restTemplate.getForObject(url, OidcPublicKeyListResponse.class);
    }
}
