package com.pocket.domain.entity;

import com.pocket.domain.dto.OidcDecodePayload;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class User extends BaseEntity {

    private String subId;
    private String name;
    private String picture;
    private String email;

    public static User createSocialUser(OidcDecodePayload oidcDecodePayload) {

        User user = new User();
        user.subId = oidcDecodePayload.sub();
        user.name = oidcDecodePayload.nickname();
        user.email = oidcDecodePayload.email();
        user.picture = oidcDecodePayload.picture();
        return user;
    }
}
