package com.pocket.core.exception.jwt;

import com.pocket.core.exception.common.BaseErrorCode;
import lombok.Getter;

@Getter
public class SecurityCustomException extends RuntimeException {

    private final BaseErrorCode errorCode;

    public SecurityCustomException(BaseErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public SecurityCustomException(BaseErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }
}
