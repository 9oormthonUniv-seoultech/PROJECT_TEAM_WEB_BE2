package com.pocket.core.exception.file;

import com.pocket.core.exception.common.BaseErrorCode;
import lombok.Getter;

@Getter
public class ImageException extends RuntimeException {

    private final BaseErrorCode errorCode;

    private final Throwable cause;

    public ImageException(BaseErrorCode errorCode) {
        this.errorCode = errorCode;
        this.cause = null;
    }

    public ImageException(BaseErrorCode errorCode, Throwable cause) {
        this.errorCode = errorCode;
        this.cause = cause;
    }
}