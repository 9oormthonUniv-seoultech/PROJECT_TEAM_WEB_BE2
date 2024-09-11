package com.pocket.core.image.exception;

import com.pocket.core.exception.common.ApiResponse;
import com.pocket.core.exception.common.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ImageErrorCode implements BaseErrorCode {

    FILE_UPLOAD_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "3000", "파일 업로드에 실패했습니다."),
    FILE_DELETE_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "3000", "파일 삭제에 실패했습니다."),
    WRONG_FILE_FORMAT(HttpStatus.INTERNAL_SERVER_ERROR, "3000", "파일 타입이 올바르지 않습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;


    @Override
    public ApiResponse<Void> getErrorResponse() {
        return null;
    }
}
