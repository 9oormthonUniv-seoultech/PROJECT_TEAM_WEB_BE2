package com.pocket.core.image.exception;

import com.pocket.core.exception.common.BaseErrorCode;

public class FileExtensionException extends ImageException {
    public FileExtensionException(BaseErrorCode errorCode) {
        super(errorCode);
    }

    public FileExtensionException(BaseErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
