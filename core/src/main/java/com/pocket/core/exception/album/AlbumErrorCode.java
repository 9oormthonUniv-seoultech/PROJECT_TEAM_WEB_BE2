package com.pocket.core.exception.album;

import com.pocket.core.exception.common.ApiResponse;
import com.pocket.core.exception.common.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AlbumErrorCode implements BaseErrorCode {
    ALBUM_NOT_FOUND(HttpStatus.BAD_REQUEST, "400", "해당 앨범이 존재하지 않습니다."),
    ALBUM_SHARE_NOT_FOUND(HttpStatus.BAD_REQUEST, "400", "해당 공유 데이터가 존재하지 않습니다."),
    USER_ALREADY_OWNED_ALBUM(HttpStatus.CONFLICT, "409", "사용자가 이미 이 앨범을 소유하고 있습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ApiResponse<Void> getErrorResponse() {
        return ApiResponse.onFailure(code, message);
    }


}
