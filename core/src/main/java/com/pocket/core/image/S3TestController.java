package com.pocket.core.image;

import com.pocket.core.exception.common.ApiResponse;
import com.pocket.core.image.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/test")
public class S3TestController {

    private final AwsS3Service awsS3Service;

    @PostMapping(value = "/uploadFile", consumes = "multipart/form-data")
    public ApiResponse<String> uploadFile(@RequestPart(value = "file", required = false) MultipartFile file) {
        return ApiResponse.onSuccess(awsS3Service.uploadFile(file));
    }
}
