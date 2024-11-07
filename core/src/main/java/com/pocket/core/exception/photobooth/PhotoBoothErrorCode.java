package com.pocket.core.exception.photobooth;

import com.pocket.core.exception.common.ApiResponse;
import com.pocket.core.exception.common.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PhotoBoothErrorCode implements BaseErrorCode {
    PHOTOBOOTH_NOT_FOUND(HttpStatus.BAD_REQUEST, "400", "해당 포토부스가 존재하지 않습니다."),
    PHOTOBOOTHBRAND_NOT_FOUND(HttpStatus.BAD_REQUEST, "400", "해당 포토부스 브랜드가 존재하지 않습니다."),
    PHOTOBOOTHLIKE_NOT_FOUND(HttpStatus.BAD_REQUEST, "400", "찜한 포토부스가 아닙니다."),
    PHOTOBOOTHLIKE_FOUND(HttpStatus.BAD_REQUEST, "400", "이미 찜한 포토부스 입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ApiResponse<Void> getErrorResponse() {
        return ApiResponse.onFailure(code, message);
    }
}
