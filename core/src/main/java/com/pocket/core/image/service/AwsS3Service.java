package com.pocket.core.image.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.pocket.core.image.exception.FileDeleteException;
import com.pocket.core.image.exception.FileExtensionException;
import com.pocket.core.image.exception.FileUploadException;
import com.pocket.core.image.exception.ImageErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AwsS3Service {

    private final AmazonS3 amazonS3;
    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    /**
     * file upload
     */
    public String uploadFile(MultipartFile multipartFile) {
        if (Objects.isNull(multipartFile)) return null;
        if (multipartFile.isEmpty()) return null;

        String fileName = createFileName(multipartFile.getOriginalFilename());

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());

        try (InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3.putObject(new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata));
        } catch (IOException e) {
            throw new FileUploadException(ImageErrorCode.FILE_UPLOAD_FAIL);
        }

        return amazonS3.getUrl(bucketName, fileName).toString();
    }


    /**
     * 파일 삭제 메서드
     */
    public void deleteFile(String fileUrl) {
        if (fileUrl == null) return;
        try {
            amazonS3.deleteObject(bucketName, fileUrl);
        } catch (AmazonServiceException e) {
            throw new FileDeleteException(ImageErrorCode.FILE_DELETE_FAIL);
        }
    }

    /**
     * 파일 업로드 시에 파일명을 난수화하는 메서드
     */
    private String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    /**
     * 파일 확장자 가져오는 메서드
     */
    private String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new FileExtensionException(ImageErrorCode.WRONG_FILE_FORMAT);
        }
    }

}
