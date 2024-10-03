package com.pocket.inbounds.user.response;

import com.pocket.domain.dto.user.UserInfoDTO;

public record UserResponse(
        String name,
        String email,
        String image
) {
}
