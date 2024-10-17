package com.pocket.core.exception.review;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ReviewCustomException extends RuntimeException {

    private final ReviewErrorCode errorCode;

    public ReviewCustomException(ReviewErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
