package com.pocket.domain.service.user;

import com.pocket.domain.dto.user.LoginResponse;
import com.pocket.domain.dto.user.UserInfoDTO;
import com.pocket.domain.port.user.LoadUserInfoPort;
import com.pocket.domain.usecase.user.LoginUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQueryService implements LoginUseCase {

    private final LoadUserInfoPort loadUserInfoPort;

    public UserInfoDTO getUserInfo(String name) {
        return loadUserInfoPort.loadUserInfo(name);
    }

    public LoginResponse reissueToken(String email) {
        return loadUserInfoPort.createToken(email);
    }
}
