package com.pocket.domain.service.file;


import com.pocket.domain.dto.file.PresignedUrlResponse;
import com.pocket.domain.port.file.FileDownloadPort;
import com.pocket.domain.port.file.FileUploadPort;
import com.pocket.domain.usecase.file.FileDownloadUseCase;
import com.pocket.domain.usecase.file.FileUploadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileService implements FileUploadUseCase {

    private final FileUploadPort fileUploadPort;

    @Override
    public PresignedUrlResponse getUploadPresignedUrl(String prefix, String fileName) {
        return fileUploadPort.getUploadPresignedUrl(prefix, fileName);
    }
}
