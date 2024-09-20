package com.pocket.core.exception.common;
import com.pocket.core.exception.photobooth.PhotoBoothCustomException;
import com.pocket.core.exception.photobooth.PhotoBoothErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PhotoBoothCustomException.class)
    public ResponseEntity<ApplicationResponse<String>> handlePhotoBoothNotFound(PhotoBoothCustomException ex) {
        ApplicationResponse<String> response = new ApplicationResponse<>(
                new ApplicationResult(Integer.parseInt(ex.getErrorCode().getCode()), ex.getErrorCode().getMessage()),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
