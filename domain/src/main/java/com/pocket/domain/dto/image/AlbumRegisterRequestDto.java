package com.pocket.domain.dto.image;

import java.util.List;

public record AlbumRegisterRequestDto(
        Long photoboothId,
        String year,
        String month,
        String date,
        List<String> hashtag,
        String memo,
        String imageName, // 이미지 이름
        String prefix // S3 버킷 내에 저장할 경로
) {
}