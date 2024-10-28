package com.pocket.domain.port.file;

import com.pocket.domain.dto.file.PresignedUrlResponse;

public interface FileUploadPort {

    PresignedUrlResponse getUploadPresignedUrl(String prefix, String fileName);

}
