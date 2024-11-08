package com.pocket.domain.dto.review;

public record ReviewMypageDetailDto(
        Long reviewId,
        String imageUrl,
        Integer month,
        Integer date,
        String photoboothName,
        Double rating
) {
}
