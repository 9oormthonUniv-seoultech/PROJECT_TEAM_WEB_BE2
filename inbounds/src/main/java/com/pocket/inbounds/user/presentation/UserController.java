package com.pocket.inbounds.user.presentation;

import com.pocket.core.aop.annotation.NameAuthenticated;
import com.pocket.core.exception.common.ApplicationResponse;
import com.pocket.domain.dto.user.UserInfoDTO;
import com.pocket.domain.usecase.user.LoginUseCase;
import com.pocket.inbounds.user.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/user")
public class UserController implements UserControllerDocs {

    private final LoginUseCase loginUseCase;

    @GetMapping
//    @NameAuthenticated
    public ApplicationResponse<UserResponse> getUserInfo(
            @AuthenticationPrincipal UserInfoDTO userInfoDTO
    ) {

        UserResponse response = new UserResponse(userInfoDTO.name(), userInfoDTO.email(), userInfoDTO.image());
        return ApplicationResponse.ok(response);
    }
}
