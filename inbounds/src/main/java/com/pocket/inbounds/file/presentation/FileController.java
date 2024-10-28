package com.pocket.inbounds.file.presentation;

import com.pocket.core.exception.common.ApplicationResponse;
import com.pocket.domain.dto.file.PresignedUrlRequest;
import com.pocket.domain.dto.file.PresignedUrlResponse;
import com.pocket.domain.service.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/file")
public class FileController implements FileControllerDocs{

    private final FileService fileService;

    @PostMapping
    public ApplicationResponse<PresignedUrlResponse> uploadPhoto(@RequestBody PresignedUrlRequest presignedUrlRequest) {
        PresignedUrlResponse response = fileService.getUploadPresignedUrl(presignedUrlRequest.getPrefix(), presignedUrlRequest.getFileName());
        return ApplicationResponse.ok(response);
    }

}
