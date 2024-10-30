package com.pocket.domain.usecase.file;

import com.pocket.domain.dto.file.PresignedUrlResponse;

public interface FileUploadUseCase {

    PresignedUrlResponse getUploadPresignedUrl(String prefix, String fileName);

}
