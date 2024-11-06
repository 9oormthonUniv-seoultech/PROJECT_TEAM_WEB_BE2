package com.pocket.core.exception.hashtag;

import com.pocket.core.exception.common.BaseErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class HashTagCustomException extends RuntimeException {

    private final BaseErrorCode errorCode;

    public HashTagCustomException(BaseErrorCode errorCode) {
        this.errorCode = errorCode;
    }

}
