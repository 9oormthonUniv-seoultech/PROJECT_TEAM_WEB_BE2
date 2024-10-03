package com.pocket.outbound.adapter.user;

import com.pocket.core.exception.user.UserCustomException;
import com.pocket.core.exception.user.UserErrorCode;
import com.pocket.domain.dto.user.UserInfoDTO;
import com.pocket.domain.port.user.LoadUserInfoPort;
import com.pocket.outbound.entity.JpaUser;
import com.pocket.outbound.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAdapter implements LoadUserInfoPort {

    private final UserRepository userRepository;

    public UserInfoDTO loadUserInfo(String name) {
        JpaUser user = userRepository.findByUserName(name)
                .orElseThrow(() -> new UserCustomException(UserErrorCode.NO_USER_INFO));

        return new UserInfoDTO(user.getUser().getName(), user.getUser().getEmail(), user.getUser().getPicture());
    }

    public UserInfoDTO loadUserInfoByEmail(String email) {
        JpaUser user = userRepository.findByUserEmail(email)
                .orElseThrow(() -> new UserCustomException(UserErrorCode.NO_USER_INFO));

        return new UserInfoDTO(user.getUser().getName(), user.getUser().getEmail(), user.getUser().getPicture());
    }
}
