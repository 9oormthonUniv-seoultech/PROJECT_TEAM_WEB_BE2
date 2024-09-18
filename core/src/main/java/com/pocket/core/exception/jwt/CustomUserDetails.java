package com.pocket.core.exception.jwt;

import org.springframework.security.core.userdetails.UserDetails;

public interface CustomUserDetails extends UserDetails {
    String getEmail(); // 이메일 주소를 반환하는 메소드 추가
}
