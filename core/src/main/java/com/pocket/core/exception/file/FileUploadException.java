package com.pocket.core.exception.file;

import com.pocket.core.exception.common.BaseErrorCode;

public class FileUploadException extends ImageException {
    public FileUploadException(BaseErrorCode errorCode) {
        super(errorCode);
    }

    public FileUploadException(BaseErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
