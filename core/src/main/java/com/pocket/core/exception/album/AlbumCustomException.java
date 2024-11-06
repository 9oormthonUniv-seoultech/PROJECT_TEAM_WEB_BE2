package com.pocket.core.exception.album;

import com.pocket.core.exception.common.BaseErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AlbumCustomException extends RuntimeException {

    private final BaseErrorCode errorCode;

    public AlbumCustomException(BaseErrorCode errorCode) {
        this.errorCode = errorCode;
    }

}
