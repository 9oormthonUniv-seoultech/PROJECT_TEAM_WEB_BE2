package com.pocket.core.exception.photobooth;

import com.pocket.core.exception.common.BaseErrorCode;
import com.pocket.core.exception.common.GlobalErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PhotoBoothCustomException extends RuntimeException {

    private final BaseErrorCode errorCode;

    public PhotoBoothCustomException(BaseErrorCode errorCode) {
        this.errorCode = errorCode;
    }


}
