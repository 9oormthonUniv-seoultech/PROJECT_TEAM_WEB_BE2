package com.pocket.domain.usecase.user;

import com.pocket.domain.dto.user.LoginResponse;
import com.pocket.domain.dto.user.UserInfoDTO;

public interface LoginUseCase {

    UserInfoDTO getUserInfo(String name);

    LoginResponse reissueToken(String email);
}
