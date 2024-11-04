package com.pocket.outbound.adapter.file.adapter;


import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.pocket.core.aop.annotation.AdapterService;
import com.pocket.domain.dto.file.PresignedUrlResponse;
import com.pocket.domain.port.file.FileUploadPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@AdapterService
@RequiredArgsConstructor
public class FileUploadAdapter implements FileUploadPort {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.expTime}")
    private Long expTime;

    private final AmazonS3 amazonS3;

    @Override
    public PresignedUrlResponse getUploadPresignedUrl(String prefix, String fileName) {
        String filePath = createPath(prefix, fileName);
        GeneratePresignedUrlRequest generatePresignedUrlRequest = getGeneratePresignedUrlRequest(bucket, filePath, HttpMethod.PUT);
        URL url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);

        return new PresignedUrlResponse(url.toString(), filePath);
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

    private String createPath(String prefix, String fileName) {
        String fileId = createFileId();
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return String.format("%s/%s-%s-%s", prefix, timestamp, fileId, fileName);
    }

    private String createFileId() {
        return UUID.randomUUID().toString();
    }

}
