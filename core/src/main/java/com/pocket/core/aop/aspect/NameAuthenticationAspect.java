package com.pocket.core.aop.aspect;

import com.pocket.core.exception.jwt.CustomUserDetails;
import com.pocket.core.exception.jwt.SecurityCustomException;
import com.pocket.core.exception.jwt.SecurityErrorCode;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class NameAuthenticationAspect {

    @Pointcut("@annotation(com.pocket.core.aop.annotation.NameAuthenticated)")
    public void nameAuthenticated() {
    }

    @Before("nameAuthenticated()")
    public void authenticateName() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = null;

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            email = userDetails.getEmail();
        }

        if (email == null) {
            throw new SecurityCustomException(SecurityErrorCode.UNAUTHORIZED);
        }
    }
}
