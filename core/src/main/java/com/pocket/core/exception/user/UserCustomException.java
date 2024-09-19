package com.pocket.core.exception.user;

import com.pocket.core.exception.common.BaseErrorCode;
import lombok.Getter;

@Getter
public class UserCustomException extends RuntimeException {

    private final BaseErrorCode errorCode;

    private final Throwable cause;

    public UserCustomException(BaseErrorCode errorCode) {
        this.errorCode = errorCode;
        this.cause = null;
    }

    public UserCustomException(BaseErrorCode errorCode, Throwable cause) {
        this.errorCode = errorCode;
        this.cause = cause;
    }
}
