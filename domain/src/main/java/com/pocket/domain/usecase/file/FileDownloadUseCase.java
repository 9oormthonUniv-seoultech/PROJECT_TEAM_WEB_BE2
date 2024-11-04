package com.pocket.domain.usecase.file;

public interface FileDownloadUseCase {

    String getDownloadPresignedUrl(String filePath);

}
