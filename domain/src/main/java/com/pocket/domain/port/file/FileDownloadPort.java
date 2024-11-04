package com.pocket.domain.port.file;

public interface FileDownloadPort {

    String getDownloadPresignedUrl(String filePath);

}
