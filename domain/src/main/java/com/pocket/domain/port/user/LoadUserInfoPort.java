package com.pocket.domain.port.user;

import com.pocket.domain.dto.user.UserInfoDTO;

public interface LoadUserInfoPort {

    UserInfoDTO loadUserInfo(String name);

}
