package com.pocket.core.exception.file;

import com.pocket.core.exception.common.BaseErrorCode;

public class FileDeleteException extends ImageException {
    public FileDeleteException(BaseErrorCode errorCode) {
        super(errorCode);
    }

    public FileDeleteException(BaseErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
