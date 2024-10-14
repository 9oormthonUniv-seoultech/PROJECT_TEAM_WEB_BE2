package com.pocket.core.exception.review;

import com.pocket.core.exception.common.ApiResponse;
import com.pocket.core.exception.common.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {
    PHOTO_FEATURE_NOT_FOUND(HttpStatus.BAD_REQUEST, "400","해당 설명에 맞는 PhotoFeature가 없습니다"),
    BOOTH_FEATURE_NOT_FOUND(HttpStatus.BAD_REQUEST, "400","해당 설명에 맞는 BoothFeature가 없습니다");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ApiResponse<Void> getErrorResponse() {
        return ApiResponse.onFailure(code, message);
    }
}
