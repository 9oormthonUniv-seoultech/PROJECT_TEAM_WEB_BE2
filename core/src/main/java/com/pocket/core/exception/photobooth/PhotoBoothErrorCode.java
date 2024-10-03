package com.pocket.core.exception.photobooth;

import com.pocket.core.exception.common.ApiResponse;
import com.pocket.core.exception.common.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PhotoBoothErrorCode implements BaseErrorCode {
    PHOTOBOOTH_NOT_FOUND(HttpStatus.BAD_REQUEST, "400", "해당 포토부스가 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ApiResponse<Void> getErrorResponse() {
        return ApiResponse.onFailure(code, message);
    }
}
