package com.pocket.outbound.adapter.file.adapter;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.domain.port.file.FileDownloadPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.net.URL;
import java.util.Date;

@AdapterService
@RequiredArgsConstructor
public class FileDownloadAdapter implements FileDownloadPort {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.expTime}")
    private Long expTime;

    private final AmazonS3 amazonS3;

    @Override
    public String getDownloadPresignedUrl(String filePath) {
        if (filePath != null && filePath.startsWith("/images/")) {
            GeneratePresignedUrlRequest generatePresignedUrlRequest = getGeneratePresignedUrlRequest(bucket, filePath, HttpMethod.GET);
            URL url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);
            return url.toString();
        }

        return filePath;
    }

    private GeneratePresignedUrlRequest getGeneratePresignedUrlRequest(String bucket, String fileName, HttpMethod method) {

        return new GeneratePresignedUrlRequest(bucket, fileName)
                .withMethod(method)
                .withExpiration(getPresignedUrlExpiration());
    }

    private Date getPresignedUrlExpiration() {
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += expTime;
        expiration.setTime(expTimeMillis);

        return expiration;
    }
}
