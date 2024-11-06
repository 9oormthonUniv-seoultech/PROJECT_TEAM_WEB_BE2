package com.pocket.domain.dto.review;

import java.util.List;

public record ReviewMypageDto(
        int reviewCount,
        List<ReviewMypageDetailDto> reviewMypageDetailDtoList
) {
}